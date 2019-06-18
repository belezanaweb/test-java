package br.com.blz.testjava.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.blz.testjava.exception.DuplicatedEntityException;
import br.com.blz.testjava.exception.EntityNotFoundException;
import br.com.blz.testjava.exception.ResponseError;

@ControllerAdvice
public class HandlerExceptionController extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler({Exception.class})
	public final ResponseEntity<ResponseError> handleGenericException(Exception ex, WebRequest request) {
	    ResponseError genericError = new ResponseError();
	    
	    if(ex instanceof DuplicatedEntityException) {
	    	genericError.setMessage("duplicated");	
	    	genericError.setCode(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
	    	return new ResponseEntity<>(genericError, HttpStatus.UNPROCESSABLE_ENTITY);
	    }else if(ex instanceof EntityNotFoundException){
	    	genericError.setMessage(ex.getMessage());	
	    	genericError.setCode(String.valueOf(HttpStatus.NO_CONTENT.value()));
	    	return new ResponseEntity<>(genericError, HttpStatus.NO_CONTENT);
	    }else {
	    	genericError.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	    	genericError.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
	    	return new ResponseEntity<>(genericError, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		   ResponseError genericError = new ResponseError();
		   genericError.setCode(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
			genericError.setMessage(ex.getMessage());
		   return new ResponseEntity<>(genericError, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
