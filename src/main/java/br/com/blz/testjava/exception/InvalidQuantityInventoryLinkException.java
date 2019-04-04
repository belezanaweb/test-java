package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.PRECONDITION_FAILED , reason="Inconsistent total and quantities in warehouse(s)")  // 412
public class InvalidQuantityInventoryLinkException extends RuntimeException {
	
	private static final long serialVersionUID = -5399690267774599641L;

	public InvalidQuantityInventoryLinkException(String message) {
		super(message);
	}

	public InvalidQuantityInventoryLinkException(Throwable cause) {
		super(cause);
	}

	public InvalidQuantityInventoryLinkException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidQuantityInventoryLinkException(String message, Throwable cause, boolean enableSuppression,boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
