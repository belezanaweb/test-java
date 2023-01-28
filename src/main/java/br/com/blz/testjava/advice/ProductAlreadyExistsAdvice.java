package br.com.blz.testjava.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.blz.testjava.exception.ProductAlreadyExistsException;

@ControllerAdvice
class ProductAlreadyExistsAdvice {

	@ResponseBody
	@ExceptionHandler(ProductAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String productAlreadyExistsdHandler(ProductAlreadyExistsException ex) {
		return ex.getMessage();
	}
}