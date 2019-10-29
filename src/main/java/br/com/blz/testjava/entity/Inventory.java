package br.com.blz.testjava.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Inventory {

	@JsonProperty(required = true)
	private List<Warehouse> warehouses;
	
}
