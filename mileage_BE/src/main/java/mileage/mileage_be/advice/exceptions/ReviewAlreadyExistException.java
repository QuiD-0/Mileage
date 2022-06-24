package mileage.mileage_be.advice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ReviewAlreadyExistException extends Exception  {
    @Getter
    HttpStatus status = HttpStatus.CONFLICT;

    public ReviewAlreadyExistException() {
        super("이미 존재하는 리뷰입니다.");
    }
}
