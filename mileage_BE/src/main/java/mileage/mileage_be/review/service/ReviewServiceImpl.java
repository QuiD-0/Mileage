package mileage.mileage_be.review.service;

import mileage.mileage_be.advice.exceptions.ContentNotExistException;
import mileage.mileage_be.advice.exceptions.NotExistActionException;
import mileage.mileage_be.advice.exceptions.UserNotFoundException;
import mileage.mileage_be.review.domain.Event;
import mileage.mileage_be.review.domain.Photo;
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

    @Override
    @Transactional
    public Review addReview(Event event) throws NotExistActionException, ContentNotExistException, UserNotFoundException {
        Optional<User> user = userService.findOne(event.getUserId());
        if (user.isEmpty()) {
            user = Optional.ofNullable(userService.join(new User(event.getUserId(), 0)));
        }
        Review review = new Review(event.getReviewId(), event.getContent(), event.getUserId(), event.getPlaceId(), null, null);
        for (String photoID : event.getAttachedPhotoIds()) {
            reviewRepository.savePhoto(new Photo(photoID, review, user.get()));
        }
        if (event.getContent().length() > 0) {
            userService.incPoint(user.get().getUserId());
        } else {
            throw new ContentNotExistException();
        }
        if (event.getAttachedPhotoIds().size() > 0) {
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
