package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProductAlreadyExistsException extends Exception {
    public ProductAlreadyExistsException(Long sku) {
        super("Product with sku " + sku + " already exists");
    }
}
