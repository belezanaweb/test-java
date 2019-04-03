package br.com.blz.testjava.business.exception;

import java.util.List;

public class InvalidWharehouseTypeException extends BusinessException {

	private static final long serialVersionUID = 4979922768754332866L;

	public InvalidWharehouseTypeException(List<String> messages) {
		super(messages);
	}

	public InvalidWharehouseTypeException(String message) {
		super(message);
	}	
	

}
