package br.com.blz.testjava.domain;

import br.com.blz.testjava.api.v1.model.WarehouseType;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Warehouse {
	private String locality;
	private int quantity;
	private WarehouseType type;
}
