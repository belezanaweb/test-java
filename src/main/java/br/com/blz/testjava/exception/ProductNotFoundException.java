package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Produto não encontrado")
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(){}
}
