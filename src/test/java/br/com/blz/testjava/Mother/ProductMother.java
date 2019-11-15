package br.com.blz.testjava.Mother;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;

@Component
public class ProductMother {

	public Product getProduct() {
		final Product domain = new Product();

		domain.setSku(null);
		domain.setName("beleza");
		domain.setInventory(null);

		return domain;
	}

	public Optional<Product> getProductOptional() {
		final Optional<Product> domain = Optional.ofNullable(new Product());
		domain.get().getSku();
		domain.get().getName();
		domain.get().getInventory();
		return domain;

	}

	public Inventory getInventory() {
		final Inventory domain = new Inventory();
		domain.setId(123);
		domain.setWarehouses(null);
		return domain;
	}

	public Warehouse getWarehouse() {
		final Warehouse domain = new Warehouse();
		domain.setId(123);
		domain.setLocality("ïndia");
		domain.setQuantity(1);
		domain.setType("blz");
		return domain;
	}

}
