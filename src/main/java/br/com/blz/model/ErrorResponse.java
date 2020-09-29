package br.com.blz.model;

public class ErrorResponse {

	private String message;
	private int code;
	
	public ErrorResponse(String message,int code) {
		this.message=message;
		this.code=code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
