package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class responsible for throwing exceptions stating that the product not found.
 * 
 * @author Andre Barroso
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor.
	 * @param message The message error.
	 */
	public ProductNotFoundException(String message) {
		super(message);
	}
	
}
