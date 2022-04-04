package br.com.blz.testjava.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
@EqualsAndHashCode(of = "sku")
public class Product {
	private Long sku;
	private String name;
	private Inventory inventory;
}
