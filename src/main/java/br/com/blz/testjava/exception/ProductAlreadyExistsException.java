package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductAlreadyExistsException extends Exception {

    

	/**
	 * 
	 */
	private static final long serialVersionUID = -3702252822424130072L;

	public ProductAlreadyExistsException(Long sku) {
        super("Product with sku "+sku+" already exists.");
    }

}
