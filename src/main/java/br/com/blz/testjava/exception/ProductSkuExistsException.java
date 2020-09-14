package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ProductSkuExistsException extends Exception {

    public ProductSkuExistsException(String message) {
        super(message);
    }
}
