package br.com.beleza.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.beleza.repository.ProductNotFoundException;

@ControllerAdvice
public class ProductNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String productNotFoundHandler(ProductNotFoundException ex) {
		return ex.getMessage();
	}
	
}
