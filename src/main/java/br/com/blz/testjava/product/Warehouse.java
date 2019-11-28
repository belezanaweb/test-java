package br.com.blz.testjava.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Warehouse {
	
	private String locality;
    private Long quantity;
    private Type type;

}
