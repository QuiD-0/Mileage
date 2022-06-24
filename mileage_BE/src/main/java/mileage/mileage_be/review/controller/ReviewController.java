package mileage.mileage_be.review.controller;

import lombok.RequiredArgsConstructor;
import mileage.mileage_be.advice.exceptions.NotExistActionException;
import mileage.mileage_be.advice.exceptions.ReviewAlreadyExistException;
import mileage.mileage_be.advice.exceptions.ReviewNotExistException;
import mileage.mileage_be.review.domain.Event;
import mileage.mileage_be.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> review(@RequestBody Event event) throws Exception {
        switch (event.getAction()) {
            case ADD: {
                //추가
                if (reviewService.findReview(event.getReviewId()).isEmpty()) {
                    reviewService.addReview(event);
                    return new ResponseEntity("성공적으로 작성되었습니다.", HttpStatus.CREATED);
                } else {
                    throw new ReviewAlreadyExistException();
                }
            }
            case MOD: {
                //수정
                if (reviewService.findReview(event.getReviewId()).isEmpty()) {
                    throw new ReviewNotExistException();
                } else {
                    reviewService.modifyReview(event, event.getReviewId());
                    return new ResponseEntity("성공적으로 수정되었습니다.", HttpStatus.OK);
                }


            }
            case DELETE: {
                //삭제
                if (reviewService.findReview(event.getReviewId()).isEmpty()) {
                    throw new ReviewNotExistException();
                } else {
                    reviewService.deleteReview(event.getReviewId());
                    return new ResponseEntity("성공적으로 삭제되었습니다.", HttpStatus.OK);
                }
            }
            default: {
                throw new NotExistActionException();
            }

        }

    }

    private Map<String, String> result = new HashMap();

    @GetMapping
    public ResponseEntity<Map<String, String>> noGET() {
        result.clear();
        result.put("msg", "GET 대신 POST로 요청 해주세요!");
        return new ResponseEntity(result, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping
    public ResponseEntity<String> noPUT() {
        result.clear();
        result.put("msg", "PUT 대신 POST로 요청 해주세요!");
        return new ResponseEntity(result, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @DeleteMapping
    public ResponseEntity<String> noDELETE() {
        result.clear();
        result.put("msg", "DELETE 대신 POST로 요청 해주세요!");
        return new ResponseEntity(result, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
