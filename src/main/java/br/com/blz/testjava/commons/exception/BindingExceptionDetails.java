package br.com.blz.testjava.commons.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BindingExceptionDetails {

	private BindingResult results;

	public BindingExceptionDetails(BindingResult results) {
		this.results = results;
	}

	public List<String> getErros() {
		return results
				.getFieldErrors()
				.stream()
				.map(this::mountMessageError)
				.collect(Collectors.toList());
	}

	private String mountMessageError(FieldError error) {
		return "O campo: " + error.getField() + ": " + error.getDefaultMessage() + " - Valor rejeitado: " + error.getRejectedValue();
	}
}
