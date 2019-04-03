package br.com.blz.testjava.rest.exception;

import java.io.Serializable;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.blz.testjava.enumeration.ValidationMessageEnum;
import br.com.blz.testjava.utils.BeanUtil;
import br.com.blz.testjava.utils.MessageResourceUtil;

public class RequestValidationResponse implements Serializable {

	private static final long serialVersionUID = 887304780400975269L;
	
	@JsonIgnore
	private FieldError error;
	
	public RequestValidationResponse(ObjectError error) {
		this.error = (FieldError) error;				
	}

	public String getMessage() {
		return BeanUtil.getBean(MessageResourceUtil.class).getMessage(
				this.error.getDefaultMessage(), 
				ValidationMessageEnum.Constants.DEFAULT_MESSAGE, 
				this.error.getArguments());
	}
	
	public String getField() {
		return this.error.getField();
	}

	
}
