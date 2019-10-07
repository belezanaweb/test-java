package br.com.blz.testjava.model;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.exception.SkuInvalidFormatException;

@Component
public class ProductValidator {

    public void validate(Product product) {
    	if(product.getSku() == null) {
    		throw new SkuInvalidFormatException("Campo Sku deve ser preenchido");
    	}
    	if(product.getName() == null) {
    		throw new SkuInvalidFormatException("Campo Name deve ser preenchido");
    	}
    	if(product.getInventory() == null) {
    		throw new SkuInvalidFormatException("Campo Inventory deve ser preenchido com");
    	}
    	if(product.getInventory().getWarehouses() == null) {
    		throw new SkuInvalidFormatException("Deve haver pelo menos uma Warehouse informada");
    	}
    	product.getInventory().getWarehouses().stream().forEach(warehouse -> {
    		if(warehouse.getType() == null) {
    			throw new SkuInvalidFormatException("Type da wharehouse deve ser preenhido");
    		}
    		if(warehouse.getLocality() == null) {
    			throw new SkuInvalidFormatException("Locality da wharehouse deve ser preenhido");
    		}
    	});
    }
}
