package br.com.blz.testjava.entity;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;
	private String message;

	public ApiError(HttpStatus status) {
		this.status = status;
	}

	public ApiError(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
}