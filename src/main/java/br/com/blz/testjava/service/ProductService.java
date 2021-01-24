package br.com.blz.testjava.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.entity.WarehousesType;
import br.com.blz.testjava.exception.ProductException;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {

    private Long SKU = (long) 43264;
    private String NAME = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";

    @Autowired
    private ProductRepository productRepository;
	
    Logger logger = LoggerFactory.getLogger(ProductService.class);
    
	public Product findBySku(Long sku) {
		
		logger.info(String.format("findBySku %s", sku));
		
		if (sku == 43264) {
			
			List<Warehouse> warehouses = new ArrayList<Warehouse>();
			
			Warehouse warehouse = new Warehouse();
			warehouse.setLocality("SP");
			warehouse.setQuantity(12);
			warehouse.setType(WarehousesType.ECOMMERCE);
			warehouses.add(warehouse);
			
			warehouse = new Warehouse();
			warehouse.setLocality("MOEMA");
			warehouse.setQuantity(3);
			warehouse.setType(WarehousesType.PHYSICAL_STORE);
			warehouses.add(warehouse);
			
			Inventory inventory = new Inventory(warehouses);
			Product product = new Product(SKU, NAME, inventory);
			
			return product;
		}

		return null;

	}
	
	public Product create(Product resquestProduct) throws ProductException {
		
		logger.info(String.format("create %s", resquestProduct));

        productRepository.save(resquestProduct);
        
        return resquestProduct;
	}
}
