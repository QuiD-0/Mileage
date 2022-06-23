package mileage.mileage_be.advice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AlreadyReviewedPlaceException extends Exception  {
    @Getter
    HttpStatus status = HttpStatus.CONFLICT;

    public AlreadyReviewedPlaceException() {
        super("해당 지역에는 이미 리뷰를 남겼습니다.");
    }
}
