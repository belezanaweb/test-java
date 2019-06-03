package br.com.blz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception para buscas onde o sku não é encontrado na base de dados
 *  
 * @author tiago
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlzProductNotFoundException extends Exception {


	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = 343655291192699853L;

	public BlzProductNotFoundException(String e) {
		super(e);
	}

}