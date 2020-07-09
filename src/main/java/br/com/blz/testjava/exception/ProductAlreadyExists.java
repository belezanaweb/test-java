package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class ProductAlreadyExists extends RuntimeException {

    public ProductAlreadyExists(){
        super();
    }
    public ProductAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
    public ProductAlreadyExists(String message) {
        super(message);
    }
    public ProductAlreadyExists(Throwable cause) {
        super(cause);
    }
}
