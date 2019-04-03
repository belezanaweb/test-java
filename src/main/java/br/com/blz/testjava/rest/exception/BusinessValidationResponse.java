package br.com.blz.testjava.rest.exception;

import java.io.Serializable;

import br.com.blz.testjava.enumeration.ValidationMessageEnum;
import br.com.blz.testjava.utils.BeanUtil;
import br.com.blz.testjava.utils.MessageResourceUtil;

public class BusinessValidationResponse implements Serializable {

	private static final long serialVersionUID = -6363549668733000454L;
	
	private final String message;
	
	public BusinessValidationResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return BeanUtil.getBean(MessageResourceUtil.class).getMessage(
				this.message, 
				ValidationMessageEnum.Constants.DEFAULT_MESSAGE);
	}
	
}
