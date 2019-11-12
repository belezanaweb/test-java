package br.com.blz.testjava.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
@Document(collection = "product")
public class Product {
	
	@Transient
    public static final String SEQUENCE_NAME = "products_sequence";

	@Id
	private Long id;
	
	private Long sku;

	private String name;

	private Inventory inventory;

	@Transient
	private Boolean isMarketable;
	
	public Product() {}

}
