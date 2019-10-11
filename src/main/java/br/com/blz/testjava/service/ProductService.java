package br.com.blz.testjava.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WarehouseDTO;

@Service
public class ProductService {
	
    private static Map<String,ProductDTO> lista = new LinkedHashMap<>();
    
    public ProductDTO search(String sku) throws Exception {
    	return lista.get(sku);
    }
    
    public ProductDTO create(ProductDTO product) throws Exception {
    	if(lista.get(product.getSku())==null) {
    		if(product.getInventory().getWarehouses()!=null && product.getInventory().getWarehouses().size()>0) {
    			Long quantity = 0L;
    			for(WarehouseDTO warehouse:product.getInventory().getWarehouses()) {
    				quantity+=warehouse.getQuantity();
    			}
    			product.getInventory().setQuantity(quantity);
    		}
    		lista.put(product.getSku(), product);
    	} else {
    		return null;
    	}
    	return lista.get(product.getSku());
    }
    
    public ProductDTO update(ProductDTO product) throws Exception {
    	if(lista.get(product.getSku())!=null) {
    		ProductDTO prodUpdate = lista.get(product.getSku());
    		if(product.getInventory().getWarehouses()!=null && product.getInventory().getWarehouses().size()>0) {
    			Long quantity = 0L;
    			for(WarehouseDTO warehouse:product.getInventory().getWarehouses()) {
    				Boolean found = false;
        			for(WarehouseDTO warehouseTemp:prodUpdate.getInventory().getWarehouses()) {
        				if(warehouseTemp.getLocality().equals(warehouse.getLocality())) {
        					found = true;
        					warehouseTemp.setQuantity(warehouseTemp.getQuantity() + warehouse.getQuantity());
        					break;
        				}
        			}
        			if(!found) {
        				prodUpdate.getInventory().getWarehouses().add(warehouse);
        			}
    				quantity+=warehouse.getQuantity();
    			}
    			prodUpdate.getInventory().setQuantity(quantity);
    		}
    		prodUpdate.setName(product.getName());
    		//lista.put(product.getSku(), product);
    	}
    	return lista.get(product.getSku());
    }
    
    public ProductDTO delete(String sku) throws Exception {
    	return lista.remove(sku);
    }
}
