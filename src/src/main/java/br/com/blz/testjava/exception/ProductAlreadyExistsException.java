package br.com.blz.testjava.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductAlreadyExistsException extends RuntimeException
{
    public ProductAlreadyExistsException(String exception) {
        super(exception);
    }
}

