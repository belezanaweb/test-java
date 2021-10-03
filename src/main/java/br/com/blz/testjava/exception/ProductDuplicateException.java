package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class ProductDuplicateException extends RuntimeException  {

    public ProductDuplicateException() {
        super("Produto já cadastrado com mesmo sku.");
    }
}
