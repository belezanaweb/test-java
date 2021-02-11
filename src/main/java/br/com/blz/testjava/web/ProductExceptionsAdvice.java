package br.com.blz.testjava.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.blz.testjava.exceptions.ProductAlreadyExistsException;
import br.com.blz.testjava.exceptions.ProductNotFoundException;

import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ProductExceptionsAdvice {

	@ResponseBody
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String productNotFoundHandler(ProductNotFoundException ex) {
		ex.printStackTrace();
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(ProductAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	String productAlreadyExistsHandler(ProductAlreadyExistsException ex) {
		ex.printStackTrace();
		return ex.getMessage();
	}

}
