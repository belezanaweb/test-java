package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotExist extends RuntimeException {

    public ProductNotExist() {
    }

    public ProductNotExist(String message) {
        super(message);
    }

}
