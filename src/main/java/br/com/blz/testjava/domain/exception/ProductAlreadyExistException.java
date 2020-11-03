package br.com.blz.testjava.domain.exception;

import org.springframework.http.HttpStatus;

public class ProductAlreadyExistException extends BusinessException {

	private static final long serialVersionUID = -4459929819263440506L;

	public ProductAlreadyExistException() {
        super("products-4", HttpStatus.CONFLICT);
    }

}
