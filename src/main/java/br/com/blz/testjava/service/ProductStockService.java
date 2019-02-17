package br.com.blz.testjava.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.mapper.ProductStockModelMapper;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductStockRepository;
import br.com.blz.testjava.view.ProductStockView;

@Service
public class ProductStockService {
	
	@Autowired
	ProductStockRepository productRepository;

	@Autowired
	ProductStockModelMapper productStockModelMapper;
	
	public Boolean hasProduct(int sku) {
		Product hasProduct = productRepository.findBySku(sku);
		
		if (hasProduct == null ) {
			return false;
		}
		else {
			return true;
		}
	}

	public Boolean add(Product product) {
		
		try {
			if (product != null) {
				
				if (hasProduct(product.getSku())) {
						productRepository.save(product);
						return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			return false;
		}
	}

	public List<ProductStockView> getList() throws Exception {
		List<Product> productList = new ArrayList<Product>();
		
		try {
			productList = productRepository.findAll();
		}
		catch(Exception e) {
			return null;
		}
		
		return productStockModelMapper.toList(productList);
	}

	public Boolean update(Product product) {
		try {
			productRepository.save(product);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

	public Boolean delete(Product product) {
		try {
			productRepository.delete(product);
		}
		catch(Exception e) {
			return false;
		}
		
		return true;
	}

}
