package br.com.blz.testjava.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repo.ProductRepository;

@Service
public class StockService {
	
	@Autowired
	ProductRepository productRepository;

	public HttpStatus add(Product product) throws Exception {
		HttpStatus httpStatus = HttpStatus.CREATED;
		
		try {
			if (product != null) {
				Product hasProduct = productRepository.findBySku(product.getSku());
				
				if (hasProduct == null ) {
						productRepository.save(product);
				}
				else {
					httpStatus = HttpStatus.CONFLICT;
				}
			}
			else {
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		}
		catch(Exception e) {
			httpStatus = HttpStatus.BAD_REQUEST;
			throw new Exception(e.getMessage());
		}
		
		return httpStatus;
	}

	public List<Product> getList() throws Exception {
		List<Product> productList = new ArrayList<Product>();
		
		try {
		productList = productRepository.findAll();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return productList;
	}

	public HttpStatus update(Product product) throws Exception {
		HttpStatus httpStatus = HttpStatus.CREATED;

		try {
			productRepository.save(product);
		}
		catch(Exception e) {
			httpStatus = HttpStatus.BAD_REQUEST;
			throw new Exception(e.getMessage());
		}
		return httpStatus;
	}

	public HttpStatus delete(Product product) throws Exception {
		HttpStatus httpStatus = HttpStatus.CREATED;

		try {
			productRepository.delete(product);
		}
		catch(Exception e) {
			httpStatus = HttpStatus.BAD_REQUEST;
			throw new Exception(e.getMessage());
		}
			
		return httpStatus;
	}

}
