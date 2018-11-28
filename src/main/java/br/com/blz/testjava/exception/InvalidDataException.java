package br.com.blz.testjava.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = 4287143716949886013L;
	
	private List<String> messages;
	
	public <T> InvalidDataException(Set<ConstraintViolation<T>> constraints){
		
		messages = new ArrayList<>();
		
		for (ConstraintViolation<T> constraint : constraints){
			messages.add(constraint.getMessage());
		}
	}

	public List<String> getMessages() {
		return messages;
	}
}
