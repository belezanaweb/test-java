package br.com.blz.testjava.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductSkuAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;

    public ProductSkuAlreadyExistsException() {
        super();
    }
}
