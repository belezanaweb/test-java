package br.com.blz.testjava.controller.custom;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.blz.testjava.custom.BusinessDuplicatedException;

public abstract class CustomErrorHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ EmptyResultDataAccessException.class, NoSuchElementException.class })
	public Map<String, String> handleMissingExceptions(Exception ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("ops", "Data not found");

		return errors;
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(BusinessDuplicatedException.class)
	public Map<String, String> handleBusinessDuplicatedException(Exception ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("ops", "Duplicated product on same sku");

		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
