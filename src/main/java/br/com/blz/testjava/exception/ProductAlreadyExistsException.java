package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ProductAlreadyExistsException extends Exception {

    public ProductAlreadyExistsException() {
        super("SKU Already Exists");
    }

}
