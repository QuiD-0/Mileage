package mileage.mileage_be.review.service;

import lombok.RequiredArgsConstructor;
import mileage.mileage_be.advice.exceptions.ContentNotExistException;
import mileage.mileage_be.advice.exceptions.NotExistActionException;
import mileage.mileage_be.advice.exceptions.ReviewNotExistException;
import mileage.mileage_be.advice.exceptions.UserNotFoundException;
import mileage.mileage_be.review.domain.Event;
import mileage.mileage_be.review.domain.Photo;
import mileage.mileage_be.review.domain.Place;
import mileage.mileage_be.review.domain.Review;
import mileage.mileage_be.review.repository.ReviewRepository;
import mileage.mileage_be.user.domain.User;
import mileage.mileage_be.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;

    //히스토리 작성하기

    @Override
    @Transactional
    public Review addReview(Event event) throws NotExistActionException, ContentNotExistException, UserNotFoundException {
        //사용자 존재 확인
        Optional<User> user = userService.findOne(event.getUserId());
        if (user.isEmpty()) {
            user = Optional.ofNullable(userService.join(new User(event.getUserId(), 0)));
        }
        //사진 저장
        Review review = new Review(event.getReviewId(), event.getContent(), event.getUserId(), event.getPlaceId(), null, null);
        for (String photoID : event.getAttachedPhotoIds()) {
            reviewRepository.savePhoto(new Photo(photoID, review, user.get()));
        }
        //컨텐츠가 있을경우 포인트 지급
        if (event.getContent().length() > 0) {
            userService.incPoint(user.get().getUserId());
        } else {
            throw new ContentNotExistException();
        }
        //사진이 있을경우 포인트 지급
        if (event.getAttachedPhotoIds().size() > 0) {
            userService.incPoint(user.get().getUserId());
        }
        //장소별 추가 포인트 지급
        if (reviewRepository.findPlaceByPlaceId(event.getPlaceId()).isEmpty()) {
            reviewRepository.savePlace(new Place(event.getPlaceId(), review, user.get()));
            userService.incPoint(user.get().getUserId());
        }


        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public Review modifyReview(Event event, String id) throws ReviewNotExistException, ContentNotExistException, UserNotFoundException, NotExistActionException {
        //기존 정보 불러오기
        Optional<Review> review = reviewRepository.findReviewById(event.getReviewId());
        if (review.isEmpty()) throw new ReviewNotExistException();
        Optional<User> user = userService.findOne(event.getUserId());
        if (user.isEmpty()) throw new UserNotFoundException();

        //이벤트 수정된 리뷰 분석
        Review modReview = new Review(event.getReviewId(), event.getContent(), event.getUserId(), event.getPlaceId(), null, null);
        if (modReview.getContent().length() == 0) throw new ContentNotExistException();
        List<String> originPhotoIds = reviewRepository.findAllPhotoIdsByReviewId(review.get().getReviewId());
        List<String> modPhotoIds = event.getAttachedPhotoIds();
        int originPhotoIdsLength = originPhotoIds.size();
        int modPhotoIdsLength = modPhotoIds.size();

        //동일한 사진체크
        boolean isSame = originPhotoIds.containsAll(modPhotoIds) && modPhotoIds.containsAll(originPhotoIds);

        //리뷰 업데이트
        if (!isSame) {
            //수정후 삭제된 사진 삭제
            for (String photoId : originPhotoIds) {
                if (!modPhotoIds.contains(photoId)) {
                    reviewRepository.removePhoto(photoId);
                }
            }
            //수정후 추가된 사진 추가
            for (String photoId : modPhotoIds) {
                if (!originPhotoIds.contains(photoId)) {
                    reviewRepository.savePhoto(new Photo(photoId, review.get(), user.get()));
                }
            }
            reviewRepository.update(modReview, event.getReviewId());
        }
        //포인트 지급 및 차감
        //사진이 1개 이상이였다가 모두 지워진 경우
        if (originPhotoIdsLength > 0 && modPhotoIdsLength == 0) {
            userService.decPoint(user.get().getUserId());
        }
        //사진이 없던 상태에서 추가된 경우
        else if (originPhotoIdsLength == 0 && modPhotoIdsLength > 0) {
            userService.incPoint(user.get().getUserId());
        }
        return modReview;
    }

    @Override
    @Transactional
    public boolean deleteReview(String reviewId) throws ReviewNotExistException, UserNotFoundException, NotExistActionException {
        //기존 정보 불러오기
        Optional<Review> review = reviewRepository.findReviewById(reviewId);
        if(review.isEmpty()) throw new ReviewNotExistException();
        Optional<User> user = userService.findOne(review.get().getUserId());
        if(user.isEmpty()) throw new UserNotFoundException();
        Optional<Place> place = reviewRepository.findPlaceByPlaceId(review.get().getPlaceId());

        //포인트 차감 및 데이터 삭제
        List<String> photoIds = reviewRepository.findAllPhotoIdsByReviewId(review.get().getReviewId());
        if(photoIds.size()>0){
            reviewRepository.removeAllPhotoByReviewId(reviewId);
            userService.decPoint(user.get().getUserId());
        }
        //사용자가 해당 지역에 최초 등록한 리뷰였을경우
        if(!place.isEmpty() && place.get().getUser().getUserId().equals(user.get().getUserId()) &&place.get().getReview().getReviewId().equals(reviewId)){
            reviewRepository.deletePlace(place.get());
            userService.decPoint(user.get().getUserId());
        }
        reviewRepository.delete(reviewId);
        userService.decPoint(user.get().getUserId());
        return true;
    }

    @Override
    public Optional<Review> findReview(String reviewId) {
        return reviewRepository.findReviewById(reviewId);
    }

}
