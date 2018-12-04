package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ProductSkuAlreadyExist extends RuntimeException{

    public ProductSkuAlreadyExist() {
    }

    public ProductSkuAlreadyExist(String message) {
        super(message);
    }
    
}
