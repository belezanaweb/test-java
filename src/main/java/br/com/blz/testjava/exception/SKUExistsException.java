package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;

public class SKUExistsException extends CommonAppException {

    public SKUExistsException() {
        super("SKU already exists", HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
