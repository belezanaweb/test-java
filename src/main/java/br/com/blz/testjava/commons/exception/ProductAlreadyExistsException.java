package br.com.blz.testjava.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
