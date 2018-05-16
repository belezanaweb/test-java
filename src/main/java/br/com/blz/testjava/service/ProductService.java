package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.infra.NotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.persistence.IProductRepository;

@Service
public class ProductService {
	private static final String ALREADY_EXISTE = "Já Existe um produto com o seguinte sku %s";
	@Autowired
	private IProductRepository repository;
	
	public boolean update(Product product) {
		return repository.update(product);
	}
	
	public void delete(Integer sku) {
		repository.remove(sku);
	}
	
	public Product getProduct(Integer sku) {
		return repository.getBySku(sku);
	}
	
	public boolean inserirProduto(Product product) throws Exception {
		if(isProductAlreadyExist(product)) {
			throw new Exception(String.format(ALREADY_EXISTE,product.getSku()));
		}
		
		return repository.insert(product);
	}
	
	private boolean isProductAlreadyExist(Product product){
		try{
			repository.getBySku(product.getSku());
		}catch(NotFoundException ex)  {
			return false;
		}
		return true;
	}
}
