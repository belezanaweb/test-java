package br.com.blz.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.blz.exception.BusinessException;
import br.com.blz.model.ErrorResponse;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(Exception e,
            HttpServletRequest request) {
    	e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(),1), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(EmptyResultDataAccessException e,
    		HttpServletRequest request) {
    	e.printStackTrace();
    	return new ResponseEntity<>(new ErrorResponse("Produto Inexistente",1), null, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(BusinessException e,
    		HttpServletRequest request) {
    	e.printStackTrace();
    	return new ResponseEntity<>(new ErrorResponse(e.getMessage(),1), null, HttpStatus.BAD_REQUEST);
    }
    
}
