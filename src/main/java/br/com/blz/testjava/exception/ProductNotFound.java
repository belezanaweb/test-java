package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Product not found")
public class ProductNotFound extends RuntimeException {

    public ProductNotFound(){
        super();
    }
    public ProductNotFound(String message, Throwable cause) {
        super(message, cause);
    }
    public ProductNotFound(String message) {
        super(message);
    }
    public ProductNotFound(Throwable cause) {
        super(cause);
    }
}
