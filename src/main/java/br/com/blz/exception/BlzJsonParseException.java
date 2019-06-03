package br.com.blz.exception;

import com.google.gson.JsonParseException;

public class BlzJsonParseException extends JsonParseException {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = -1412410966702151778L;

	public BlzJsonParseException(String msg) {
		super(msg);
	}
	
	public BlzJsonParseException(Throwable cause) {
		super(cause);
	}

}
