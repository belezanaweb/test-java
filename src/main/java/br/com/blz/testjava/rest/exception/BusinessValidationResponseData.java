package br.com.blz.testjava.rest.exception;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class BusinessValidationResponseData implements Serializable {

	private static final long serialVersionUID = 2931013645894524146L;
	
	private final List<BusinessValidationResponse> data;
	
	public BusinessValidationResponseData(List<String> messages) {
		this.data = messages.stream().map(message -> {
			return new BusinessValidationResponse(message);
		}).collect(Collectors.toList());
	}

	public List<BusinessValidationResponse> getData() {
		return data;
	}
	
}
