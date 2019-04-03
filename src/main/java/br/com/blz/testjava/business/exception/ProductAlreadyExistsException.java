package br.com.blz.testjava.business.exception;

import java.util.List;

public class ProductAlreadyExistsException extends BusinessException {

	private static final long serialVersionUID = 1134629738039601510L;

	public ProductAlreadyExistsException(List<String> messages) {
		super(messages);
	}

	public ProductAlreadyExistsException(String message) {
		super(message);
	}

}
