package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Repository;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.ProductException;

@Repository
public class ProductRepository {
	
	private static Map<Long, Product> products = new HashMap<>();

    public void save(Product product) throws ProductException {

    	Long sku = product.getSku();

        if(!Objects.isNull(products.get(sku))) {
			throw new ProductException("Produto já cadastrado.");
        }

        products.put(product.getSku(), product);
    }
    
}