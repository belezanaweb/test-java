package br.com.blz.testjava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.domain.Product;

@Service
public class ProductService implements ProductServiceInterface{
	
	List<Product> productList = new ArrayList<>();

	@Override
	public void persistProduct(Product product) {
		
		Product productObj = findProduct(product.getSku());
		
		if (productList.contains(productObj))
			throw new IllegalArgumentException("Product already exists");
		
		productList.add(product);
	}

	@Override
	public Boolean updateProduct(Long productSku, Product product) {
			
		Product productObj = findProduct(productSku);
		
		if (productObj != null) {
			
			productObj.setSku(productSku);
			productObj.setName(product.getName());
			productObj.getInventory().setWarehouses(product.getInventory().getWarehouses());
			
			return true;
		}
		
		return false;
	}

	@Override
	public Product findProduct(Long productSku) {
		
		Optional<Product> product = productList.stream()
				.filter(pElement -> pElement.getSku().equals(productSku))
				.findFirst();
		
		if (product.isPresent()) {
			
			Long warehousesQuantity = product.get()
					.getInventory()
					.getWarehouses()
					.stream()
					.mapToLong(w -> w.getQuantity())
					.sum();
			
			product.get().getInventory().setQuantity(warehousesQuantity);
			
			if (warehousesQuantity > 0) {
				
				product.get().setIsMarketable(true);
				
			} else {
				
				product.get().setIsMarketable(false);
			}
			
			return product.get();	
		}
		
		return null;		
	}

	@Override
	public Boolean deleteProduct(Long productSku) {
		
		Product product = findProduct(productSku);
		
		for (int position = 0 ; position < productList.size();  ) {
			
			if(productList.get(position) == product) {
				productList.remove(position);
				return true;
			}
			
			position++;
		}
		
		return false;
	}

}
