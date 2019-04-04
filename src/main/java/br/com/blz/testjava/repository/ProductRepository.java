package br.com.blz.testjava.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.blz.testjava.exception.InvalidIdException;
import br.com.blz.testjava.exception.ProductIdAlreadyInUseException;
import br.com.blz.testjava.exception.ProductNotExistentException;
import br.com.blz.testjava.exception.UnableToGetItemsQuantityException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.validator.ProductValidator;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProductRepository {
	private static final Logger LOG = LoggerFactory.getLogger(ProductRepository.class);
	
	private static Map<Long, Product> productRepo;
	private AtomicLong id;
	
	@Autowired
	public ProductRepository() {
		if(productRepo == null)
			productRepo = new HashMap();
		
		if(id == null)
			id = new AtomicLong(1);
	}
	
	public Product insert(Product newProduct) {
		if(productRepo.get(newProduct.getSku()) != null)
			throw new ProductIdAlreadyInUseException("There is another product stored with the informed id.");
		
		Long id = checkId(newProduct);
		ProductValidator.validate(newProduct);
		
		productRepo.put(id, newProduct);
		return newProduct;
	}

	private Long checkId(Product newProduct) {
		Long id = newProduct.getSku();
		if(isInvalidId(id)) {
			LOG.error("Invalid id informed. A new one will be generated.");
			id = this.id.getAndIncrement();
			newProduct.setSku(id);
		}
		return id;
	}

	private boolean isInvalidId(Long id) {
		return id == null || id < Long.valueOf(1L);
	}

	public Product get(Long sku) {
		Product product = productRepo.get(sku);
		product = prepareProductToBeRetrieved(product);
		
		return product;
	}

	private Product prepareProductToBeRetrieved(Product product) {
		if(product == null) {
			throw new ProductNotExistentException("Product not found");
		}
		Inventory inventory = product.getInventory();
		if(inventory == null || inventory.getWarehouses() == null || inventory.getWarehouses().isEmpty()) {
			throw new UnableToGetItemsQuantityException("Unable to compute total quantity of items in stock");
		}
		
		long total = 0L;
		for(Warehouse ware : inventory.getWarehouses()) {
			total += ware.getQuantity();
		}
		inventory.setQuantity(total);
		product.setInventory(inventory);
		product.setIsMarketable(total > 0);
		
		return product;
	}

	public Product update(Product newProduct) {
		if(isInvalidId(newProduct.getSku())) {
			throw new InvalidIdException("Unable to update product due invalid id");
		}
		ProductValidator.validate(newProduct);
		
		productRepo.replace(newProduct.getSku(), newProduct);
		return newProduct;
	}

	public Product delete(Long sku) {
		return productRepo.remove(sku);
	}

	public List<Long> testGetAll() {
		Iterator<Long> keys = productRepo.keySet().iterator();
		List<Long> keysList = new ArrayList();
		while(keys.hasNext()) {
			keysList.add(keys.next());
		}
		return keysList;
	}
}
