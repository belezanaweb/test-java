package br.com.blz.testjava.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.blz.testjava.constant.ProductConstant;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouses;

public class ProductMock {

	public static Product getProductDTO() {
		final Product product = new Product();
		
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium -"
				+ " Máscara de Reconstrução 500g");
		product.setSku(43264L);		
		
		final Warehouses warehousesOne = new Warehouses();
		warehousesOne.setLocality("SP");
		warehousesOne.setIdWarehouse(1L);
		warehousesOne.setProduct(product);
		warehousesOne.setType(ProductConstant.ECOMMERCE);
		warehousesOne.setQuantity(12L);
		
		final Warehouses warehousesTwo = new Warehouses();
		warehousesTwo.setLocality("MOEMA");
		warehousesTwo.setIdWarehouse(1L);
		warehousesTwo.setProduct(product);
		warehousesTwo.setType(ProductConstant.PHYSICAL_STORE);
		warehousesTwo.setQuantity(12L);
		
		final List<Warehouses> warehouses = new ArrayList<Warehouses>();
		warehouses.add(warehousesTwo);
		warehouses.add(warehousesTwo);
		
		product.setWarehouses(warehouses);		
		
		return product;
	}
	
	public static List<Product> getProductsDTO() {
		final List<Product> products = new ArrayList<Product>();
		products.add(getProductDTO());
		return products;
	}
	
	public static ProductDTO getNewProductDTO() {
		final ProductDTO dto = new ProductDTO();
		
		dto.setName("L'Oréal new");
		dto.setSku(43265L);		
		return dto;
	}
	
	public static Product getNewProduct() {
		final Product product = new Product();		
		product.setName("L'Oréal new");
		product.setSku(43265L);		
		return product;
	}
}
