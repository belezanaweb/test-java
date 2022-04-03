package br.com.blz.testjava.api.v1.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class WarehouseDTO {
	private String locality;
	private Integer quantity;
	private WarehouseType type;
}
