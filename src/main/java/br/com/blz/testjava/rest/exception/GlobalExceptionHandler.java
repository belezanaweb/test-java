package br.com.blz.testjava.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.blz.testjava.business.exception.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RequestValidationResponseData> requestValidationError(MethodArgumentNotValidException e) {		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new RequestValidationResponseData(e.getBindingResult().getAllErrors()));
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Void> requestHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		return ResponseEntity.badRequest().build();
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<BusinessValidationResponseData> businessValidationError(BusinessException e) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(new BusinessValidationResponseData(e.getMessages()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Void> genericError(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
