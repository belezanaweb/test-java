package br.com.blz.testjava.rest.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.ObjectError;

public class RequestValidationResponseData {

	private List<RequestValidationResponse> data;

	public RequestValidationResponseData(List<ObjectError> errors) {
		this.data = errors.stream().map(error -> {
			return new RequestValidationResponse(error);
		}).collect(Collectors.toList());
	}
	
	public List<RequestValidationResponse> getData() {
		return data;
	}

	public void setData(List<RequestValidationResponse> data) {
		this.data = data;
	}
	
}
