package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductSkuException.class)
	public final ResponseEntity<?> handleProductSkuException(ProductSkuException ex) {
		return new ResponseEntity<>(getError(ex, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex) {
		return new ResponseEntity<>(getError(ex, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}

	private ErrorDetails getError(ApplicationException ex, HttpStatus status) {
		ErrorDetails errorDetail = new ErrorDetails();
		errorDetail.setStatus(status.value());
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDeveloperMessage(ex.getClass().getName());

		return errorDetail;
	}
}
