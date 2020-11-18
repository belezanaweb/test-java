package br.com.blz.testjava.common.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.blz.testjava.dto.Response;
import br.com.blz.testjava.dto.Response.Message;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = { BusinessException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		BusinessException businessException = (BusinessException) ex;
		
		List<Message> messages = businessException.getErros().stream().map(currentMessage -> {
			return Message.builder()
					.code(currentMessage.getCode())
					.description(currentMessage.getMessage())
					.build();
		}).collect(Collectors.toList());
		
		return ResponseEntity
				.badRequest()
				.body(Response.builder()
						.messages(messages)
						.build());
	}
}
