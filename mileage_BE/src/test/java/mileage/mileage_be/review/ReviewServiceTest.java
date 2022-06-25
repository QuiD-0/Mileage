package mileage.mileage_be.review;

import mileage.mileage_be.advice.exceptions.*;
import mileage.mileage_be.review.domain.Action_type;
import mileage.mileage_be.review.domain.Event;
import mileage.mileage_be.review.domain.Review;
import mileage.mileage_be.review.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;


    @Test
    @Transactional
    public void addReviewWhenReviewedPlace() throws UserNotFoundException, NotExistActionException, ContentNotExistException, AlreadyReviewedPlaceException {
        Event event = new Event("REVIEW", Action_type.ADD, "review_id1", "좋아요!", List.of("test_photoId1", "test_photoId2"), "user_id1", "place_id1");

        reviewService.addReview(event);
        //동일지역 리뷰 추가
        Assertions.assertThrows(AlreadyReviewedPlaceException.class, () -> {
            reviewService.addReview(event);
        });
    }

    @Test
    @Transactional
    public void addReviewWhenContentNotExist() throws UserNotFoundException, NotExistActionException, ContentNotExistException, AlreadyReviewedPlaceException {
        Event event = new Event("REVIEW", Action_type.ADD, "review_id1", "", List.of("test_photoId1", "test_photoId2"), "user_id1", "place_id1");

        //컨텐츠가 없는 event 저장
        Assertions.assertThrows(ContentNotExistException.class, () -> {
            reviewService.addReview(event);
        });
    }

    @Test
    @Transactional
    public void addReview() throws UserNotFoundException, NotExistActionException, ContentNotExistException, AlreadyReviewedPlaceException {
        Event event = new Event("REVIEW", Action_type.ADD, "review_id1", "test", List.of("test_photoId1", "test_photoId2"), "user_id1", "place_id1");

        Review review = reviewService.addReview(event);

        Assertions.assertEquals(review.getReviewId(), event.getReviewId());
        Assertions.assertEquals(review.getContent(), event.getContent());
        Assertions.assertEquals(review.getPlaceId(), event.getPlaceId());
        Assertions.assertEquals(review.getUserId(), event.getUserId());

    }


    @Test
    @Transactional
    public void modReviewWhenUserNotExist() throws UserNotFoundException, NotExistActionException, ContentNotExistException, AlreadyReviewedPlaceException {
        Event event1 = new Event("REVIEW", Action_type.MOD, "review_id1", "test", List.of("test_photoId1", "test_photoId2"), "user_id1", "place_id1");
        Event event2 = new Event("REVIEW", Action_type.MOD, "review_id1", "test", List.of("test_photoId1", "test_photoId2"), "user_id2", "place_id1");

        reviewService.addReview(event1);

        //존재하지 않는 사용자가 글 수정
        Assertions.assertThrows(UserNotFoundException.class, () ->
                reviewService.modifyReview(event2, event2.getReviewId())
        );
    }


    @Test
    @Transactional
    public void modReviewWhenReviewNotExist() throws UserNotFoundException, NotExistActionException, ContentNotExistException, AlreadyReviewedPlaceException {
        Event event1 = new Event("REVIEW", Action_type.MOD, "review_id1", "test", List.of("test_photoId1", "test_photoId2"), "user_id1", "place_id1");
        Event event2 = new Event("REVIEW", Action_type.MOD, "review_id2", "test", List.of("test_photoId1", "test_photoId2"), "user_id1", "place_id1");

        reviewService.addReview(event1);

        //존재하지 글을 수정
        Assertions.assertThrows(ReviewNotExistException.class, () ->
                reviewService.modifyReview(event2, event2.getReviewId())
        );
    }


}
