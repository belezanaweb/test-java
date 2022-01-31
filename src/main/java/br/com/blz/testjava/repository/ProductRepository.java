package br.com.blz.testjava.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.data.MemoryData;
import br.com.blz.testjava.model.Product;

@Repository
public class ProductRepository {

	@Autowired
	MemoryData memory;
	
	
	public Boolean insert(Product product) {
		if (memory.insert(product)) {
			return true;
		}
		return false;
	}
		
	public Boolean update(Product product) {
		return memory.update(product);
	}
	
	public Boolean delete(Long sku) {
		return memory.delete(sku);
	}
	
	
	public Product getProductBySKU(Long sku) {
		return memory.getProductBySku(sku);
	}
	
	public List<Product> listAll(){
		return memory.getProducts();
	}
}
