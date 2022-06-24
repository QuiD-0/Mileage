package mileage.mileage_be.advice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ReviewNotExistException extends Exception  {
    @Getter
    HttpStatus status = HttpStatus.NO_CONTENT;

    public ReviewNotExistException() {
        super("존재하지 않는 리뷰입니다.");
    }
}
