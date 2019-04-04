package br.com.blz.testjava.validator;

import br.com.blz.testjava.exception.InvalidMarketableProductException;
import br.com.blz.testjava.exception.InvalidProductNameException;
import br.com.blz.testjava.exception.InvalidQuantityInventoryLinkException;
import br.com.blz.testjava.exception.InvalidTotalProductQuantityException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

public class ProductValidator {

	private ProductValidator() {}
	
	public static void validate(Product product) {
		if(product.getName() == null)
			throw new InvalidProductNameException("The product name must not be null.");
		if(product.getName().isEmpty())
			throw new InvalidProductNameException("The product name must not be empty.");
		if(product.isIsMarketable() == null)
			throw new InvalidMarketableProductException("A product can only be marketable or not (true/false).");
		if(product.getInventory() != null) 
			validateInventory(product.getInventory());
	}

	private static void validateInventory(Inventory inventory) {
		validateQuantity(inventory);
		
		if(inventory.getWarehouses() == null) {
			inventory.setQuantity(0L);
			return; // to avoid nullPointer in for
		}
		
		long productsInWarehouses = 0;
		for (Warehouse warehouse : inventory.getWarehouses()) 
			productsInWarehouses += warehouse.getQuantity();
		
		boolean quantityAccordingToWarehouses = (inventory.getQuantity() == productsInWarehouses);
		
		if(!quantityAccordingToWarehouses) 
			throw new InvalidQuantityInventoryLinkException("Inventory quantity must be equal of quantities in all warehouses");
	}

	private static void validateQuantity(Inventory inventory) {
		if(inventory.getQuantity() == null)
			inventory.setQuantity(0L);
			
		if(inventory.getQuantity() < 0)
			throw new InvalidTotalProductQuantityException("Product quantity must be integer zero or positive.");
	}
}
