package br.com.blz.testjava.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.enums.WarehouseType;

@Repository
public class ProductRepository {

	List<Product> products = new ArrayList<>();

	public Product findBy(int sku) {
		
		return products.stream()
				.filter(p -> p.getSku() == sku)
				.findAny()
				.orElse(null);		
	}

	public Product addProduct(Product product) {
		boolean anyMatch = products.stream().anyMatch(p -> p.getSku() == product.getSku());
		
		if(!anyMatch)				
			products.add(product);
		else
			throw new IllegalArgumentException("Can't add duplicated sku");
		
		return product;
	}

	public boolean deleteProductBy(int sku) {
		return products.removeIf(p -> p.getSku() == sku);		
	}

	public Product updateProduct(int sku, Product product) {
		Product toUpdate = findBy(sku);
		toUpdate.setName(product.getName());
		toUpdate.setInventory(product.getInventory());
		return toUpdate;
	}

	@PostConstruct
	public List<Product> createMockProducts() {

		Inventory inventory = Inventory.valueOf(Arrays.asList(
				Warehouse.valueOf("SP", 10, WarehouseType.ECOMMERCE),
				Warehouse.valueOf("MOEMA", 2, WarehouseType.PHYSICAL_STORE)));
		
		products.add(Product.valueOf(48533, "the Balm Girls Getaway Trio - Paleta de Maquiagem 3,3g", inventory));
		products.add(Product.valueOf(50618,	"Maybelline The Falsies Push Up Drama Waterproof  - Máscara para Cílios 9,7ml", inventory));
		products.add(Product.valueOf(19163, "M·A·C Haute & Naughty Too Black - Máscara para Cílios 9g", inventory));
		products.add(Product.valueOf(38352, "Catharine Hill Sombras Variadas 1017 - Paleta de Sombras 24g", inventory));
		products.add(Product.valueOf(24410,	"LOréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", inventory));

		return products;
	}
}