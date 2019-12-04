package br.com.blz.testjava.model.dto;

public class ValidationExceptionDTO {
	private String field;
	private String message;
	public String getField() {
		return field;
	}
	public String getMessage() {
		return message;
	}
	
	public ValidationExceptionDTO(String field, String message) {
		this.field = field;
		this.message = message;
	}
}
