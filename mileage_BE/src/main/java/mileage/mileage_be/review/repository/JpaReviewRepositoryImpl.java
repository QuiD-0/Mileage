package mileage.mileage_be.review.repository;

import lombok.RequiredArgsConstructor;
import mileage.mileage_be.review.domain.Photo;
import mileage.mileage_be.review.domain.Place;
import mileage.mileage_be.review.domain.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaReviewRepositoryImpl implements ReviewRepository {

    private final EntityManager em;

    @Override
    public Review save(Review review) {
        em.persist(review);
        return review;
    }

    @Override
    public Review update(Review review, String id) {
        Review updatedReview = em.find(Review.class, id);
        updatedReview.setContent(review.getContent());
        return updatedReview;
    }

    @Override
    public boolean delete(String reviewId) {
        Review review = em.find(Review.class, reviewId);
        em.remove(review);
        return true;
    }

    @Override
    public Optional<Review> findReviewById(String reviewId) {
        Review review = em.find(Review.class, reviewId);
        return Optional.ofNullable(review);
    }

    @Override
    public void savePlace(Place place) {
        em.persist(place);
    }

    @Override
    public void deletePlace(Place place) {
        em.remove(place);
    }

    @Override
    public Optional<Place> findPlaceByPlaceId(String placeId) {
        Place place = em.find(Place.class, placeId);
        return Optional.ofNullable(place);
    }

    @Override
    public Long findAllReviewedPlaceByUserId(String userId, String placeId) {
        return (Long) em.createQuery("select count(r) from Review r where r.userId=:userId and r.placeId=:placeId").setParameter("userId", userId).setParameter("placeId", placeId).getSingleResult();
    }

    @Override
    public void savePhoto(Photo photo) {
        em.persist(photo);
    }

    @Override
    public void removePhoto(String photoId, String reviewId) {
        Photo photo = findPhotoByPhotoId(photoId, reviewId);
        em.remove(photo);
    }

    @Override
    public List<String> findAllPhotoIdsByReviewId(String reviewId) {
        Optional<Review> review = findReviewById(reviewId);
        List<Photo> photos = em.createQuery("select p from Photo p where p.photoEmbededId.review = :review ", Photo.class).setParameter("review", review.get()).getResultList();
        List<String> photoIds = new ArrayList<>();
        for (Photo photo : photos) {
            photoIds.add((photo.getPhotoEmbededId().getAttachedPhotoIDs()));
        }
        return photoIds;
    }

    @Override
    public Photo findPhotoByPhotoId(String photoId, String reviewId) {
        return (Photo) em.createQuery("select p from Photo p where p.photoEmbededId.attachedPhotoIDs=:photoId and p.photoEmbededId.review.reviewId=:reviewId").setParameter("photoId", photoId).setParameter("reviewId", reviewId).getSingleResult();
    }


    @Override
    public void removeAllPhotoByReviewId(String reviewId) {
        em.createQuery("delete from Photo p where p.photoEmbededId.review.reviewId=:reviewId").setParameter("reviewId", reviewId).executeUpdate();
    }

}
