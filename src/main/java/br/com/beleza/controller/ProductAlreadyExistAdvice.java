package br.com.beleza.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.beleza.repository.ProductAlreadyExistException;

@ControllerAdvice
public class ProductAlreadyExistAdvice {

	@ResponseBody
	@ExceptionHandler(ProductAlreadyExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String productAlreadyExistHandler(ProductAlreadyExistException ex) {
		return ex.getMessage();
	}
}
