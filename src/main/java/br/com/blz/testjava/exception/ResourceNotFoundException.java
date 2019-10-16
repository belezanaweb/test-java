package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends CommonAppException {

    public ResourceNotFoundException() {
        super("Resource not found.", HttpStatus.NOT_FOUND);
    }

}
