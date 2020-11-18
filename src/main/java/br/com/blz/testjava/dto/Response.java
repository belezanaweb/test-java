package br.com.blz.testjava.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response<T extends Object> {

	private T result;
	private List<Message> messages;
	
	@Builder
	@Data
	public static class Message {
		private String code;
		private String description;
	}
}
