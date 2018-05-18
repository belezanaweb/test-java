package br.com.blz.testjava.service;

import static br.com.blz.testjava.dao.PersistenceEntity.getInstance;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.exception.InvalidProductSkuException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;

@Component
final class ProductServiceImpl implements ProductService {

	@Override
	public Integer saveOrUpdate(Product product) {
		checkNotNull(product);

		final Map<Integer, Product> savedProducts = getInstance().findAll();
		final Integer sku = product.getSku();
		final Inventory inventory = product.getInventory();
		if (inventory != null) {
			inventory.calculeQuantity();
			product.calculeMarkeable();
		}
		savedProducts.put(sku, product);
		return sku;
	}

	@Override
	public Product findBySku(Integer sku) throws InvalidProductSkuException {
		final Optional<Product> optionalSavedProduct = getInstance().findBySku(sku);
		return optionalSavedProduct.orElseThrow(() -> new InvalidProductSkuException());
	}

	@Override
	public Product delete(Integer sku) {
		final Optional<Product> optionalSavedProduct = getInstance().findBySku(sku);
		if (!optionalSavedProduct.isPresent()) {
			throw new InvalidProductSkuException();
		}
		return getInstance().delete(sku);
	}

}
