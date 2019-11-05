package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Não é possível alterar o campo SKU do produto")
public class ProductSkuImmutableException extends RuntimeException {
    public ProductSkuImmutableException(){}
}
