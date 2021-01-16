package br.com.blz.testjava.infrastructure.exception;

import lombok.ToString;

public class ApplicationException extends RuntimeException {

    protected ApplicationException(String message) {
        super(message);
    }
}
