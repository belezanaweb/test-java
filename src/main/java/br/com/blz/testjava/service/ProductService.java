package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;

public final class ProductService {

	public static final Product updateQuantityAndMarketable(final Product product) {
		int quantity = product.getInventory().getWarehouses().stream().mapToInt(warehouse -> warehouse.getQuantity())
				.reduce(0, (a, b) -> a + b);

		product.getInventory().setQuantity(quantity);

		product.setMarketable(quantity > 0);

		return product;
	}

}
