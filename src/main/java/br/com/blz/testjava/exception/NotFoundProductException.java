package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundProductException extends Exception {

    public NotFoundProductException(){}

    public NotFoundProductException(String message) {
        super(message);
    }
}
