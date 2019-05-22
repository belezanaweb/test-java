package br.com.blz.testjava.service.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author filipe
 * Ref.: https://alidg.me/blog/2016/9/24/rest-api-error-handling
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -4001387802812353671L;
	
	private final String code;
	private final HttpStatus status;

	public BusinessException(String code, HttpStatus status) {
		this.code = code;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
