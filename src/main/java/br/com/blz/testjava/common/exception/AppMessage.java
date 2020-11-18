package br.com.blz.testjava.common.exception;

import br.com.blz.testjava.business.domain.GlobalMessage;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class AppMessage {
	
	private String code;
	private String message;
	
	public static AppMessage create(String code, String message) {
		return AppMessage.builder()
				.code(code)
				.message(message)
				.build();
	}
	
	public static AppMessage create(GlobalMessage message) {
		return AppMessage.builder()
				.code(message.getCode())
				.message(message.getDescription())
				.build();
	}
}
