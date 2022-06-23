package mileage.mileage_be.review.service;

import mileage.mileage_be.advice.exceptions.ContentNotExistException;
import mileage.mileage_be.advice.exceptions.NotExistActionException;
import mileage.mileage_be.advice.exceptions.ReviewNotExistException;
import mileage.mileage_be.advice.exceptions.UserNotFoundException;
import mileage.mileage_be.review.domain.Event;
import mileage.mileage_be.review.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review addReview(Event event) throws NotExistActionException, ContentNotExistException, UserNotFoundException;

    Review modifyReview(Event event, String id) throws ReviewNotExistException, ContentNotExistException, UserNotFoundException, NotExistActionException;

    boolean deleteReview(String reviewID) throws ReviewNotExistException, UserNotFoundException, NotExistActionException;

    Optional<Review> findReview(String reviewId);


}
