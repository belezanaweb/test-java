package br.com.blz.testjava.handler;

public class ErrorResponse {
	public ErrorResponse(String code, String message, String cause) {
		super();
		this.code = code;
		this.message = message;
		this.cause = cause;
	}
	private  String code;
    private  String message;
    private  String cause;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
}
