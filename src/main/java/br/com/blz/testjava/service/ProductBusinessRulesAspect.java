package br.com.blz.testjava.service;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import br.com.blz.testjava.dao.entity.Inventory;
import br.com.blz.testjava.dao.entity.Product;
import br.com.blz.testjava.dao.entity.Warehouse;	

@Aspect
@Configuration
public class ProductBusinessRulesAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterReturning(value = "execution ( * br.com.blz.testjava.service.ProductService.findById (..) ) ", 
			returning = "product")
	public void afterReturningSearch(JoinPoint joinPoint, Product product) {
		Inventory inventory = product.getInventory();
		if(inventory!=null) {
			inventory.setQuantity(calculateInventoryQuantity(inventory));
		}
		product.isMarketable(isMarketable(inventory));
		logger.info("{} returned product sku value {}", joinPoint, product.getSku());
	}	
	
    public Long calculateInventoryQuantity(Inventory inventory) {
		List<Warehouse> warehouses = inventory.getWarehouses();
		if(warehouses==null) {
			return 0L;
		}
		return warehouses.stream().mapToLong(w -> w.getQuantity()).sum();
    }
    
	public Boolean isMarketable(Inventory inventory) {
		if(inventory!=null && inventory.getQuantity() > 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}    
}