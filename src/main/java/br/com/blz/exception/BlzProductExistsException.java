package br.com.blz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception para tentativa de criação de Sku já existente na base de dados
 * 
 * @author tiago
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlzProductExistsException extends Exception {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 2184230572474293149L;

	public BlzProductExistsException(String e) {
		super(e);
	}

}