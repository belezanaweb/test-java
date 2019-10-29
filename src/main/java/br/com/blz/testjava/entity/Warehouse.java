package br.com.blz.testjava.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Warehouse {

	@JsonProperty(required = true)
	private String locality;

	@JsonProperty(required = true)
	private Integer quantity;

	@JsonProperty(required = true)
	private String type;
	
}
