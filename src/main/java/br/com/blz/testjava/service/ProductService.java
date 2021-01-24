package br.com.blz.testjava.service;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Product;

@Service
public class ProductService {

    private Long SKU = (long) 43264;
    private String NAME = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
    private String INVETORY = "inventory123";

	public Product findBySku(long sku) {
		
		if (sku == 43264)
			return new Product(SKU, NAME, INVETORY);
		else 
			return null;

	}

}
