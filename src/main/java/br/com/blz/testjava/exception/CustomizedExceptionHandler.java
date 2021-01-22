package br.com.blz.testjava.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler{
	
	

	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<Error> handleBusinessExceptions(BusinessException ex, WebRequest request) {
	  Error errorDetails = new Error(LocalDate.now(), ex.getMessage(),
	      request.getDescription(false));
	  return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Error> handleExceptions(Exception ex, WebRequest request) {
	  Error errorDetails = new Error(LocalDate.now(), "Ocorreu erro interno, favor contactar a equipe responsável.",
	      request.getDescription(false));
	  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
