package br.com.blz.testjava.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.dto.ProductsDTO;
import br.com.blz.testjava.exception.EntityInvalidException;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService{
    
	@Autowired
	private ProductRepository repository;
	
	ModelMapper mapper = new ModelMapper();
	
	@Override
	public Product saveProduct(ProductsDTO product) {
		Product prod = null;
		if(repository.findBySku(product.getSku()) == null ) {
			prod = new Product();
			prod.setMarketable(true);
			prod.setName(product.getName());
			prod.setSku(product.getSku());
			
			Inventory inv = new Inventory();
			inv.setQuantity(product.getInventory().getQuantity());
			
			//Type listType = new TypeToken<Warehouse>(){}.getType();
			//List<Warehouse> insert = mapper.map(product.getInventory().getWarehouses(),listType);
			List<Warehouse> insert = null;
			for(int i = 0; i < product.getInventory().getWarehouses().size(); i++) {
				insert = new ArrayList<Warehouse>();
				Warehouse n = new Warehouse();
				n.setLocality(product.getInventory().getWarehouses().get(i).getLocality());
				n.setQuantity(product.getInventory().getWarehouses().get(i).getQuantity());
				n.setType(product.getInventory().getWarehouses().get(i).getType());
				insert.add(n);
				
			}
			inv.setWarehouse(insert);
			prod.setInventory(inv);
			
			prod = repository.save(prod);
		}else {
			throw new EntityInvalidException("Dois produtos são considerados iguais se os seus skus forem iguais");
		}
		return prod;
		
	}

	@Override
	public Product findBySku(Long sku) {
		Product p = repository.findBySku(sku);
		p.getInventory().setQuantity(p.getInventory().getWarehouse().size());
		if(p.getInventory().getQuantity() > 0) {
			p.setMarketable(true);
		}else {
			p.setMarketable(false);
		}
		return p;
	}

}
