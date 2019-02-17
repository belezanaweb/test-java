package br.com.blz.testjava.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.view.ProductStockView;

@Component
public class ProductStockModelMapper {
	
	public ProductStockView to(Product product) {
		ProductStockView productStockView = new ProductStockView();

		if (product != null) {
			
			productStockView.setName(product.getName());
			productStockView.setSku(product.getSku());
			productStockView.setInventory(product.getInventory());
			productStockView.setIsMarketable(product.getIsMarketable());
		}
		
		return productStockView;
		
	}

	public List<ProductStockView> toList(List<Product> products) {
		List<ProductStockView> productListView = new ArrayList<ProductStockView>();
		
		for (Product product : products) {
			productListView.add(this.to(product));
		}
		
		return productListView;
	}
}
