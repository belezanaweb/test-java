package br.com.belezaNaWeb.javaTest.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.belezaNaWeb.javaTest.domain.Product;

@Repository
public class ProductRepository {

	private List<Product> productList;
	Logger logger = LoggerFactory.getLogger(ProductRepository.class);

	public ProductRepository() {
		productList = new ArrayList<Product>();
		while (productList.size() < 8) {
			productList.add(new Product(Long.valueOf(productList.size() + 1), "Product " + (productList.size() + 1)));
			logger.info("New Product by sku : {}", productList.get(productList.size() - 1));
		}
	}

	public List<Product> findAll() {
		return productList;
	}

	public Product findBySku(Long sku) {
		return productList.isEmpty() ? null
				: productList.stream()
						.filter(p -> p.getSku() == sku)
						.findFirst()
						.orElse(null);
	}

	public boolean create(Product product) {
		return productList.add(product);
	}

	public void update(Product product) {
		Product productNew = findBySku(product.getSku());
		productNew.setName(product.getName());
		productNew.setInventory(product.getInventory());
		
		productList.remove(product);
		productList.add(productNew);
	}

	public void delete(Product product) {
		productList.remove(product);
	}

}
