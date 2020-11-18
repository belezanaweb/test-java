package br.com.blz.testjava.common.exception;

import java.util.ArrayList;
import java.util.List;

import br.com.blz.testjava.business.domain.GlobalMessage;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private List<AppMessage> messages;
	
	public BusinessException(GlobalMessage message) {
		AppMessage appMessage = AppMessage.builder()
				.code(message.getCode())
				.message(message.getDescription())
				.build();
		
		if (messages == null) {
			messages = new ArrayList<AppMessage>();
		}
		
		messages.add(appMessage);
	}

	public List<AppMessage> getErros() {
		return messages;
	}
}
