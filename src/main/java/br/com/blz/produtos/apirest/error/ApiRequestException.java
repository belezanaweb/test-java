package br.com.blz.produtos.apirest.error;

import org.springframework.http.HttpStatus;


public class ApiRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private final HttpStatus httpStatus;

	
	public ApiRequestException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
		// TODO Auto-generated constructor stub
	}

	public ApiRequestException(String message, Throwable cause, HttpStatus httpStatus) {
		super(message, cause);
		this.httpStatus = httpStatus;
		// TODO Auto-generated constructor stub
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	
	
}
