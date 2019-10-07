package br.com.blz.testjava.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductConflictException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ProductConflictException(String error) {
        super(error);
    }
}
