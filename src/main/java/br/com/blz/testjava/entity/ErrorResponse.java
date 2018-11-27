package br.com.blz.testjava.entity;

public class ErrorResponse {

	private String message;

	public ErrorResponse() {
	}
	
	public ErrorResponse(String message) {
		this.message = message;
	}

	public static ErrorResponse valueOf(String message) {
		return new ErrorResponse(message);
	}
	
	public String getMessage() {
		return message;
	}
	
}
