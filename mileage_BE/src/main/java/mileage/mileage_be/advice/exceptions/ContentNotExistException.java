package mileage.mileage_be.advice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ContentNotExistException extends Exception  {
    @Getter
    HttpStatus status = HttpStatus.NO_CONTENT;

    public ContentNotExistException() {
        super("content가 존재하지 않습니다. 한자 이상 리뷰를 남겨주세요");
    }
}
