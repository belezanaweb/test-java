package br.com.blz.testjava.service.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BusinessException {

	private static final long serialVersionUID = 5706865390376186164L;

	public EntityNotFoundException() {
		super("products-4", HttpStatus.NOT_FOUND);
	}

}
