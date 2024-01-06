package pl.adrianix2000.Engboost.exceptions;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public class AppException extends RuntimeException {

    @Getter
    private final HttpStatus status;

    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
