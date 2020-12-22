package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private String code;

    public NotFoundException(String message) {
        super(message);
    }
}
