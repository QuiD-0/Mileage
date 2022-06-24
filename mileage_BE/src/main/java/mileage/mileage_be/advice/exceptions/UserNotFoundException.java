package mileage.mileage_be.advice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends Exception  {
    @Getter
    HttpStatus status = HttpStatus.NOT_FOUND;

    public UserNotFoundException() {
        super("존재하지 않는 사용자입니다.");
    }
}
