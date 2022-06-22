package mileage.mileage_be.review.repository;

import mileage.mileage_be.review.domain.Photo;
import mileage.mileage_be.review.domain.Place;
import mileage.mileage_be.review.domain.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class JpaReviewRepositoryImpl implements ReviewRepository {

    private final EntityManager em;

    public JpaReviewRepositoryImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public Review save(Review review) {
        em.persist(review);
        return review;
    }

    @Override
    public Review update(Review review, String id) {
        return null;
    }

    @Override
    public boolean delete(String reviewID) {
        return false;
    }

    @Override
    public Optional<Review> findReviewById(String reviewId) {
        Review review = em.find(Review.class,reviewId);
        return Optional.ofNullable(review);
    }

    @Override
    public List<Review> FindAllReviewByUserId(String userId) {
        return null;
    }

    @Override
    public void savePhoto(Photo photo) {
        em.persist(photo);
    }

    @Override
    public void savePlace(Place place) {
        em.persist(place);
    }

    @Override
    public Optional<Place> findPlaceByPlaceId(String placeId) {
        Place place = em.find(Place.class,placeId);
        return Optional.ofNullable(place);
    }


    @Override
    public List<Review> findAllReviewByPlaceId(String placeId) {
        return em.createQuery("select r from Place p join Review r where p.placeId=:placeId and p.placeId = r.placeId order by r.createdAt",Review.class).getResultList();
    }
}
