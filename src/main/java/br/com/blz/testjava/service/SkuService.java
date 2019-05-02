package br.com.blz.testjava.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.domain.Product;

@Service
public class SkuService {
	
	private List<Product> skuList = new ArrayList<>();
	
	public Product findBySku(Integer sku){
		
		try {
			return this.skuList.stream().filter(p -> p.getSku().equals(sku)).findFirst().get();
		} catch (Exception e) {
			throw new IllegalArgumentException("Produto não encontrado!");
		}
	}
	
	public List<Product> findAll() {
		return this.skuList;
	}
	
	public void createSku(Product product) {
		if(this.validate(product)) 
			throw new IllegalArgumentException("Produto já cadastrado com a sku: " + product.getSku());
		
		this.skuList.add(product);
	}

	private boolean validate (Product product) {
		return this.skuList.stream().filter(s -> s.getSku().equals(product.getSku())).count() > 0;
	}

	public void updateProduct(Integer sku, Product product) {
		try {
			this.findBySku(sku);
		} catch (Exception e) {
			throw new IllegalArgumentException("Produto não encontrado!");
		}
//		if(!this.validate(this.findBySku(sku)))
		
		int r = this.skuList.indexOf(product);
		this.skuList.set(r, product);
	}
	
	public void deleteProduct(Integer sku) {
		Product prod = this.findBySku(sku);
		
		if(!this.validate(prod))
			throw new IllegalArgumentException("Produto não encontrado!");
		
		
		this.skuList.remove(prod);		
	}
	
}
