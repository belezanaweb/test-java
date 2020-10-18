package br.com.blz.testjava.api.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ProductExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ProductAlreadyExistsException.class)
	public ResponseEntity<ErrorEntity> alreadyExistsException(final ProductAlreadyExistsException e) {
		ErrorEntity error = new ErrorEntity("ProductExists", e.getMessage());
		return  new ResponseEntity<ErrorEntity>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductNotExistsException.class)
	public ResponseEntity<ErrorEntity> notExistsException(final ProductNotExistsException e) {
		ErrorEntity error = new ErrorEntity("ProductNotExists", e.getMessage());
		return  new ResponseEntity<ErrorEntity>(error, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

		final List<ErrorEntity> errors = new ArrayList<ErrorEntity>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(new ErrorEntity(error.getField(), error.getDefaultMessage()));
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(new ErrorEntity(error.getObjectName(), error.getDefaultMessage()));
		}

		log.error("The following constraints have been violated {}", errors.toString());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}
	
	@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
		
		ErrorEntity error = new ErrorEntity();
		
		log.error("Error on read payload {}", ex);
              
        error.setCode(request.getContextPath());
        error.setError("Invalid Payload");
       
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

	/*
	 * private ResponseEntity<VndErrors> error(final Exception exception, final
	 * HttpStatus httpStatus, final String logRef) { final String message =
	 * Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName
	 * ()); return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
	 * }
	 * 
	 * @ExceptionHandler(IllegalArgumentException.class) public
	 * ResponseEntity<VndErrors> assertionException(final IllegalArgumentException
	 * e) { return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage()); }
	 */

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
		List<ErrorEntity> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(new ErrorEntity(violation.getPropertyPath().toString(),
					violation.getMessage().concat(", Value invalid: " + violation.getInvalidValue())));
		}
		log.error("The following constraints have been violated {}", errors.toString());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
