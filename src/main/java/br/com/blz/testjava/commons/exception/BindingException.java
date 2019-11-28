package br.com.blz.testjava.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BindingException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private BindingResult results;
	
	public BindingException(BindingResult results) {
		this.results = results;
	}
	
	public BindingResult getResults() {
		return results;
	}

}