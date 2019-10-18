package br.com.blz.testjava.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ProdutoException  extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProdutoException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}

	public ProdutoException(String errorMessage) {
		super(errorMessage);
	}

	public ProdutoException(Throwable cause) {
		super(cause);
	}

}
