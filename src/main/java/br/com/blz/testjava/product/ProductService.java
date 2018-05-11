package br.com.blz.testjava.product;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

@Service
final class ProductService {
	
	final ProductRepository productRepository = ProductRepository.INSTANCE;

	public Product create(final Product product) {
		final Optional<Product> findedProduct = productRepository.findBySku(product.getSku());
		if(findedProduct.isPresent()){
			throw new ProductAlreadyExistException();
		}
		productRepository.saveProduct(product);
		return product;
	}

	public Product update(final Integer sku, final Product newProduct) {
		final Optional<Product> findedProduct = productRepository.findBySku(sku);
		if(!findedProduct.isPresent()){
			throw new ProductNotFoundException();
		}
		productRepository.deleteProduct(sku);
		productRepository.saveProduct(newProduct);
		return newProduct;
	}

	public String delete(final Integer sku) {
		final Optional<Product> findedProduct = productRepository.findBySku(sku);
		if(!findedProduct.isPresent()){
			throw new ProductNotFoundException();
		}
		productRepository.deleteProduct(sku);
		return "Produto com sku: " + sku + " removido.";
	}

	public Product find(final Integer sku) {
		final Optional<Product> optionalProduct = productRepository.findBySku(sku);
		if (!optionalProduct.isPresent()) {
			throw new ProductNotFoundException();
		}

		final Product product = optionalProduct.get();
		final int warehouseQuantities = product.getInventory().getWarehouses().stream()
				.mapToInt(Warehouse::getQuantity)
				.sum();
		product.getInventory().setQuantity(warehouseQuantities);
		if (warehouseQuantities > 0) {
			product.setMarketable(true);
		}

		return product;
	}

}
