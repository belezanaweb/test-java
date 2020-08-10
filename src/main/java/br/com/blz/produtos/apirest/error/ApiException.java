package br.com.blz.produtos.apirest.error;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;


public class ApiException {
	
	private String message;
	private int httpCode;
	private HttpStatus httpStatus;
	private ZonedDateTime timestamp;
	private Throwable detail;
	
	public ApiException() {
		super();
	}
	
	public ApiException(String message, int httpCode, HttpStatus httpStatus, ZonedDateTime timestamp) {
		super();
		this.message = message;
		this.httpCode = httpCode;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
	}
	
	public ApiException(String message, int httpCode, HttpStatus httpStatus, ZonedDateTime timestamp, Throwable detail) {
		super();
		this.message = message;
		this.httpCode = httpCode;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
	}


	public String getMessage() {
		return message;
	}


	public int getHttpCode() {
		return httpCode;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}


	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public Throwable getDetail() {
		return detail;
	}
	

	
}
