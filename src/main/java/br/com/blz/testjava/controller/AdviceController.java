/**
 * 
 */
package br.com.blz.testjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.blz.testjava.exception.ProductException;

/**
 * @author ocean
 *
 */
@ControllerAdvice
public class AdviceController {	

	@ExceptionHandler(ProductException.class)
	public ResponseEntity<String> exceptionHandler(final ProductException exception) {
		return new ResponseEntity<>(exception.getCodMsg() + " - " + exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
