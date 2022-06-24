package mileage.mileage_be.review.service;

import mileage.mileage_be.advice.exceptions.*;
import mileage.mileage_be.review.domain.Event;
import mileage.mileage_be.review.domain.Review;

import java.util.Optional;

public interface ReviewService {
    Review addReview(Event event) throws NotExistActionException, ContentNotExistException, UserNotFoundException, AlreadyReviewedPlaceException;

    Review modifyReview(Event event, String id) throws ReviewNotExistException, ContentNotExistException, UserNotFoundException, NotExistActionException;

    boolean deleteReview(String reviewID) throws ReviewNotExistException, UserNotFoundException, NotExistActionException;

    Optional<Review> findReview(String reviewId);


}
