package br.com.blz.testjava.validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import br.com.blz.testjava.exception.DupItemsInWarehousesException;
import br.com.blz.testjava.exception.InvalidProductNameException;
import br.com.blz.testjava.exception.InvalidQuantityInventoryLinkException;
import br.com.blz.testjava.exception.InvalidTotalProductQuantityException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProductValidator {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductValidator.class);
	
	private ProductValidator() {}
	
	public static void validate(Product product) {
		if(product.getName() == null)
			throw new InvalidProductNameException("The product name must not be null.");
		if(product.getName().isEmpty())
			throw new InvalidProductNameException("The product name must not be empty.");
		if(product.isIsMarketable() == null)
			product.setIsMarketable(Boolean.FALSE);
		if(product.getInventory() != null) 
			validateInventory(product);
	}

	private static void validateInventory(Product product) {
		Inventory inventory = product.getInventory();
		validateQuantity(inventory);
		
		if(inventory.getWarehouses() == null) {
			inventory.setQuantity(0L);
			return; // to avoid nullPointer in for
		}
		

//		(s1, s2) ->
//		(	s1.getLocality().equals(s2.getLocality())
//		&&  s1.getType().equals(s2.getType()) ) ||
//		( ( s1.getLocality() == null && s2.getLocality() == null ) && 
//		( s1.getType() == null && s2.getType() == null ) )  ?
//				0 : 1
		
		Set<Warehouse> warehouseSet = new ConcurrentSkipListSet<Warehouse>( // below: Comparator
				new Comparator<Warehouse>() {
					@Override
					public int compare(Warehouse s1, Warehouse s2) {
						String local1 = s1.getLocality() == null ? "" : s1.getLocality();
						String local2 = s2.getLocality() == null ? "" : s2.getLocality();
						
						if(local1.compareTo(local2) != 0) return 1;
						
						String type1 = s1.getType() == null ? "" : s1.getType();
						String type2 = s2.getType() == null ? "" : s2.getType();
						
						return type1.compareTo(type2);
					}
				}
		);
		warehouseSet.addAll(inventory.getWarehouses());
		
		// To make inventory only with different items
		if(warehouseSet.size() != inventory.getWarehouses().size()) {
			throw new DupItemsInWarehousesException("Remove duplicated items of the warehouses list");
		}
		
		long productsInWarehouses = 0;
		for (Warehouse warehouse : warehouseSet) {
			if(warehouse.getQuantity() == null ) 
				warehouse.setQuantity(0L);
			
			if(warehouse.getQuantity().longValue() < 0) 
				throw new InvalidQuantityInventoryLinkException("All warehouses must have zero or more items");
			
			productsInWarehouses += warehouse.getQuantity();
		}
		
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
