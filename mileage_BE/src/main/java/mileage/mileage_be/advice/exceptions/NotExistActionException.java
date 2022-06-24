package mileage.mileage_be.advice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotExistActionException extends Exception  {
    @Getter
    HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;

    public NotExistActionException() {
        super("존재하지 않는 actions입니다.");
    }
}
