package mileage.mileage_be.advice;

import mileage.mileage_be.advice.exceptions.NotExistActionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotExistActionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse exception(final NotExistActionException e) {
        final ErrorResponse response = new ErrorResponse(e.getMessage(), e.getStatus());
        return response;
    }
}
