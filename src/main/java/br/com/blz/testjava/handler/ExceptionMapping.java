package br.com.blz.testjava.handler;

import org.springframework.http.HttpStatus;

public class ExceptionMapping {

	public ExceptionMapping(String message, String code, HttpStatus status) {
		super();
		this.message = message;
		this.code = code;

		this.status = status;
	}

	private String message;
	private String code;
	private HttpStatus status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
