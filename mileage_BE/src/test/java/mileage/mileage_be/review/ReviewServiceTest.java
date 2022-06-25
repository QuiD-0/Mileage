package mileage.mileage_be.review;

import mileage.mileage_be.advice.exceptions.AlreadyReviewedPlaceException;
import mileage.mileage_be.advice.exceptions.ContentNotExistException;
import mileage.mileage_be.advice.exceptions.NotExistActionException;
import mileage.mileage_be.advice.exceptions.UserNotFoundException;
import mileage.mileage_be.history.service.HistoryService;
import mileage.mileage_be.review.domain.Action_type;
import mileage.mileage_be.review.domain.Event;
import mileage.mileage_be.review.service.ReviewService;
import mileage.mileage_be.user.service.UserService;
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
    public void add_review_when_reviewed_place() throws UserNotFoundException, NotExistActionException, ContentNotExistException, AlreadyReviewedPlaceException {
        Event event = new Event("REVIEW", Action_type.ADD, "review_id1", "좋아요!", List.of("test_photoId1", "test_photoId2"), "user_id1", "place_id1");

        reviewService.addReview(event);
        //동일지역 리뷰 추가
        Assertions.assertThrows(AlreadyReviewedPlaceException.class, () -> {
            reviewService.addReview(event);
        });
    }
}
