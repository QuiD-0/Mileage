package mileage.mileage_be.review.repository;

import mileage.mileage_be.review.domain.Photo;
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
}
