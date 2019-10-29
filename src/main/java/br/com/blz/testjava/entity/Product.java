package br.com.blz.testjava.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Product {
	
	@JsonProperty(required = true)
	private Integer sku;

	@JsonProperty(required = true)
	private String name;

	@JsonProperty(required = true)
	private Inventory inventory;

}
