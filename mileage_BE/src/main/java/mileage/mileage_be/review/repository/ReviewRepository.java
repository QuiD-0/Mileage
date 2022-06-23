package mileage.mileage_be.review.repository;

import mileage.mileage_be.review.domain.Photo;
import mileage.mileage_be.review.domain.Place;
import mileage.mileage_be.review.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);

    Review update(Review review, String id);

    boolean delete(String reviewID);

    Optional<Review> findReviewById(String reviewId);


    void savePhoto(Photo photo);

    void savePlace(Place place);

    Optional<Place> findPlaceByPlaceId(String placeId);


    void removePhoto(String photoId);

    List<String> findAllPhotoIdsByReviewId(String reviewId);

    Photo findPhotoByPhotoId(String photoId);

    void deletePlace(Place place);

    void removeAllPhotoByReviewId(String reviewId);

    List<String> findAllReviewedPlaceByUserId(String userId);
}
