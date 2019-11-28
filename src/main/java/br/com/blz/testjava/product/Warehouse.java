package br.com.blz.testjava.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Warehouse {
	
	private String locality;
    private Long quantity;
    private Type type;

}
