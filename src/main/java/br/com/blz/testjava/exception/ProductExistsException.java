package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Um produto já foi cadastrado com esse sku")
public class ProductExistsException extends RuntimeException {
    public ProductExistsException(){}
}
