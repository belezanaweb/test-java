package br.com.blz.testjava.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductAlreadyExistException   extends RuntimeException {

    private String message;

    public ProductAlreadyExistException() {}

    public ProductAlreadyExistException(String msg) {
            super(msg);
            this.message = msg;
            log.warn(msg);
    }
}
