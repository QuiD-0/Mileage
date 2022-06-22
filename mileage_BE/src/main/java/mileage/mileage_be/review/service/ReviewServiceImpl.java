package mileage.mileage_be.review.service;

import mileage.mileage_be.advice.exceptions.ContentNotExistException;
import mileage.mileage_be.advice.exceptions.NotExistActionException;
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
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
    }
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
        if(reviewRepository.findPlaceByPlaceId(event.getPlaceId()).isEmpty()){
            reviewRepository.savePlace(new Place(event.getPlaceId(),review,user.get()));
            userService.incPoint(user.get().getUserId());
        }


        return reviewRepository.save(review);
    }

    @Override
    public Review modifyReview(Event event, String id) {
        return null;
    }

    @Override
    public boolean deleteReview(String reviewID) {
        return false;
    }

    @Override
    public Optional<Review> findReview(String reviewId) {
        return reviewRepository.findReviewById(reviewId);
    }

    @Override
    public List<Review> FindOnesReviews(String userId) {
        return null;
    }
}
