package br.com.blz.testjava.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WarehouseDTO;
import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.exception.ApiException;
import br.com.blz.testjava.exception.InvalidDataException;

@Service
public class BlzWebProductService {
	
	@Autowired
	Validator validator;
	
	// representacao da persistencia
	private static HashSet<Product> products = new HashSet<Product>();

	public ProductDTO get(Long sku){

		ProductDTO prodDTO;
		
		//se nao encontrar, por sku, retorna nulo
		prodDTO = products.stream().filter(p -> p.getSku().equals(sku)).findFirst().map(p -> this.mapFrom(p)).orElse(null);
		
		this.calculateInventory(prodDTO);
		return prodDTO;
	}
	
	public ProductDTO create(ProductDTO dto){
	
		if (dto.getSku() != null && dto.getSku() > 0L){
			throw new ApiException("ERRO_SKU_ESPEC");
		}
		
		this.validateProduct(dto);
		
		//gerando chave baseado na hora (pois nao ha sequence persistida)
		dto.setSku(System.currentTimeMillis());
		
		Product p = this.mapTo(dto);
		products.add(p);
		
		this.calculateInventory(dto);
		return dto;
	}
	
	public ProductDTO update(Long sku, ProductDTO prodDTO){

		Product prod = this.findProduct(sku);
		this.validateProduct(prodDTO);

		Product newProd = this.mapTo(prodDTO);
		newProd.setSku(prod.getSku());
		products.add(newProd);
		
		ProductDTO newDTO;
		newDTO = this.mapFrom(newProd);
		
		this.calculateInventory(newDTO);
		return newDTO;
	}
	
	public void delete(Long sku){
		
		Product p = this.findProduct(sku);
		products.remove(p);
	}
	
	private Product findProduct(Long sku){
		
		if (sku == null || sku == 0L){
			throw new ApiException("ERRO_SKU_NESPEC");
		}
		
		Optional<Product> opt = products.stream().filter(p -> p.getSku() == sku).findFirst();
		
		if (opt.isPresent()){
			return opt.get();
			
		} else {
			throw new ApiException("PROD_NOT_FOUND");
		}
	}
	
	private void calculateInventory(ProductDTO prodDTO){

		if (prodDTO != null) {
			int quantity = 0;

			for (WarehouseDTO wareDTO : prodDTO.getInventory().getWarehouses())
				quantity += wareDTO.getQuantity();

			prodDTO.getInventory().setQuantity(quantity);
			prodDTO.setIsMarketable(quantity > 0);
		}
	}
	
	private void validateProduct(ProductDTO prodDTO){
		
		this.validate(prodDTO);
		this.validate(prodDTO.getInventory());
		
		for (WarehouseDTO wareDTO : prodDTO.getInventory().getWarehouses()){
			this.validate(wareDTO);
		}
	}
	
	private <T> void validate(T bean) {

		Set<ConstraintViolation<T>> constraints;
		constraints = this.validator.validate(bean);

		if (!constraints.isEmpty())
			throw new InvalidDataException(constraints);
	}
	
	//substituir posteriormente por Dozer ou BeanUtils (copia de properties)
	private ProductDTO mapFrom(Product p){
		
		ProductDTO prodDTO = new ProductDTO();
		prodDTO.setSku(p.getSku());
		prodDTO.setName(p.getName());
		
		InventoryDTO invDTO = new InventoryDTO();
		prodDTO.setInventory(invDTO);

		invDTO.setWarehouses(new ArrayList<WarehouseDTO>());

		for (Warehouse wh : p.getInventory().getWarehouses()){
			WarehouseDTO wareDTO = new WarehouseDTO();
			wareDTO.setLocality(wh.getLocality());
			wareDTO.setQuantity(wh.getQuantity());
			wareDTO.setType(wh.getType());
			
			invDTO.getWarehouses().add(wareDTO);
		}
		
		return prodDTO;
	}
	
	//substituir posteriormente por Dozer ou BeanUtils (copia de properties)
	private Product mapTo(ProductDTO p){
		
		Product prod = new Product();
		prod.setSku(p.getSku());
		prod.setName(p.getName());
		
		Inventory inv = new Inventory();
		prod.setInventory(inv);
		
		inv.setWarehouses(new ArrayList<Warehouse>());
		
		for (WarehouseDTO wareDTO : p.getInventory().getWarehouses()){
			Warehouse wh = new Warehouse();
			wh.setLocality(wareDTO.getLocality());
			wh.setQuantity(wareDTO.getQuantity());
			wh.setType(wareDTO.getType());
			
			inv.getWarehouses().add(wh);
		}
		
		return prod;
	}
	
}
