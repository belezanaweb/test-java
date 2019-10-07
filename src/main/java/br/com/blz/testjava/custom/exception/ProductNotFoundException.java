package br.com.blz.testjava.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends  RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String error) {
        super(error);
    }
}
