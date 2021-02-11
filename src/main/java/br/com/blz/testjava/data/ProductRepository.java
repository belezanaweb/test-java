package br.com.blz.testjava.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.exceptions.ProductAlreadyExistsException;
import br.com.blz.testjava.exceptions.ProductNotFoundException;

@Component
public class ProductRepository {
	
	private Map<Long, Product> repository = new HashMap<>();

	public Optional<Product> findBySku(Long sku) {

		return Optional.ofNullable(repository.get(sku));
	}

	public Collection<Product> findAll() {
		return repository.values();
	}

	public void save(Product product) {
		
		if (repository.containsKey(product.getSku()))
			throw new ProductAlreadyExistsException(product.getSku());
		
		repository.put(product.getSku(), product);
	}

	public void deleteBySku(Long sku) {
		
		if (repository.remove(sku) == null)
			throw new ProductNotFoundException(sku);
		
	}

	public Product update(Long sku, Product newProduct) {
		
		return findBySku(sku)
				.map(product -> {
					product.setName(newProduct.getName());
					product.setInventory(newProduct.getInventory());
					return product;
				})
				.orElseThrow(() -> new ProductNotFoundException(sku));

	}

}
