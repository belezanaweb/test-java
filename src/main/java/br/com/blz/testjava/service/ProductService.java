package br.com.blz.testjava.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.BusinessException;

@Service
public class ProductService {

	private static List<Product> products = new ArrayList<>();

	public void insert(Product product) throws Exception {

		if (!products.contains(product)) {
			products.add(product);
		} else {
			throw new BusinessException("Produto já existente com sku informado!");
		}
	}

	public Boolean delete(Long sku) {

		Product product = findProductBySku(sku);
		if(product != null) {
			return products.remove(product);
		}else {
			return Boolean.FALSE;
		}
	
	}

	public void update(Product product) throws BusinessException {

		Product productFound = findProductBySku(product.getSku());
		if (productFound != null) {
			Integer indexProduct = products.indexOf(productFound);
			products.set(indexProduct, product);
		} else {
			throw new BusinessException("Produto com sku informado não encontrado para edição!");
		}
	}

	public Product findProductBySku(Long sku) {

		return products.stream().filter(p -> p.getSku().equals(sku)).findAny().orElse(null);
	}
	
	public Product recoverProductBySku(Long sku) {
		
		Product productFound = findProductBySku(sku);
		if (productFound != null) {
			
			calculateInventoryQuantity(productFound);
			calculateMarketableProperty(productFound);
		} 
		
		return productFound;
	}

	private void calculateInventoryQuantity(Product product) {

		Integer sum = product.getInventory().getWarehouses().stream().map(x -> x.getQuantity()).reduce(0, Integer::sum);
		product.getInventory().setQuantity(sum);

	}

	private void calculateMarketableProperty(Product product) {
		
		product.setIsMarketable(product.getInventory().getQuantity().intValue() > BigDecimal.ZERO.intValue());
	}

}
