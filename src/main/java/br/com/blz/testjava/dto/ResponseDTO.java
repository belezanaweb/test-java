package br.com.blz.testjava.dto;

public class ResponseDTO {

	private String message;
	
	

	public ResponseDTO(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
