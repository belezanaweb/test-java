package br.com.blz.testjava.exceptionhandler;

import java.util.Arrays;
import java.util.List;

public class ApiError {

	private int code;
	private List<String> messages;

	public ApiError() {
		super();
	}

	public ApiError(final int code, final List<String> messages) {
		super();
		this.code = code;
		this.messages = messages;
	}

	public ApiError(final int code, final String message) {
		super();
		this.code = code;
		messages = Arrays.asList(message);
	}

	public int getCode() {
		return code;
	}

	public void setStatus(final int code) {
		this.code = code;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(final List<String> messages) {
		this.messages = messages;
	}

	public void setMessage(final String message) {
		messages = Arrays.asList(message);
	}

}
