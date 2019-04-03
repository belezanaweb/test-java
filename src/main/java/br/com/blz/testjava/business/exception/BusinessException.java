package br.com.blz.testjava.business.exception;

import java.util.ArrayList;
import java.util.List;

public abstract class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 3216783364957731586L;
	
	private List<String> messages;

	public BusinessException(List<String> messages) {
		this.messages = messages;
	}
	
	public BusinessException(String message) {
		if (this.messages == null) {
			this.messages = new ArrayList<String>();
		}
		this.messages.add(message);
	}	
	
	public List<String> getMessages() {
		return messages;
	}
	
	@Override
	public String getMessage() {
		if (this.getMessages() != null && !this.getMessages().isEmpty()) {
			return this.getMessages().get(0);
		}
		return null;
	}

}
