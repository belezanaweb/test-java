package br.com.blz.testjava.api.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorEntity {

	@JsonProperty("code")
	private String code;
	@JsonProperty("error")
	private String error;
}
