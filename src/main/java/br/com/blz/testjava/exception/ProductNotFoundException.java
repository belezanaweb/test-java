package br.com.blz.testjava.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductNotFoundException extends RuntimeException {

    private String message;

    public ProductNotFoundException() {}

    public ProductNotFoundException(String msg) {
            super(msg);
            this.message = msg;
            log.warn(msg);
    }
}
