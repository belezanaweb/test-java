package br.com.blz.testjava.services;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entities.Inventory;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.entities.Warehouse;

@Service
public class ProductService {
	/*
	 * - Sem interface e implementação por conta do teste estar num escopo mais simples e fechado.
	   - Controle de exeções no default do Spring, também por conta da simplicidade. Dentro de um escopo mais complexo, 
	 gosto do @RestContrellerAdvicer com um Handler de Exceções customizado.
	 * - Padrão de retorno com HTTP Status e ResponseEntity com a exceção da validação de negócio.
	*/
	public ResponseEntity<?> create(Product inProduct) {
			Product arrayProd = productsArray.stream().filter(p -> inProduct.equals(p)).findAny().orElse(null);
			if (arrayProd == null) {
				productsArray.add(inProduct);				
				return ResponseEntity.ok().body(productsArray.get(productsArray.indexOf(inProduct)));
			} else {
				return ResponseEntity.badRequest().body("Dois produtos são considerados iguais se os seus skus forem iguais");				
			}
	}
	
	public ResponseEntity<?> getBySku(Long sku) {
		Product arrayProd = productsArray.stream().filter(filterProd -> sku.equals(filterProd.getSku())).findAny().orElse(null);
		if (arrayProd != null){
			return ResponseEntity.status(HttpStatus.FOUND).body(calculateProductInventory(arrayProd));			
		} 
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<?> deleteBySku(Long sku) {
		Product arrayProd = productsArray.stream().filter(filterProd -> sku.equals(filterProd.getSku())).findAny().orElse(null);
		if (productsArray.remove(arrayProd))
			return ResponseEntity.ok().build();
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<?> updateBySku(Product inProduct) {
		Product arrayProd = productsArray.stream().filter(p -> inProduct.equals(p)).findAny().orElse(null);
		if (arrayProd != null) {
			productsArray.set(productsArray.indexOf(arrayProd), inProduct);
			return ResponseEntity.ok().body(productsArray.get(productsArray.indexOf(arrayProd)));
		} else {
			return ResponseEntity.badRequest().body("Dois produtos são considerados iguais se os seus skus forem iguais");				
		}
	}
	
	/*
	 - Calculo de Inventário baseado na descriçõ do negócio.
	*/
	private Product calculateProductInventory(Product inProduct){
		Long quantity = inProduct.getInventory().getWarehouses().stream().mapToLong(Warehouse::getQuantity).sum();
		Boolean isMarketable = quantity > 0 ? true : false;
		return Product.builder()
				.sku(inProduct.getSku())
				.name(inProduct.getName())
				.inventory(new Inventory(quantity, inProduct.getInventory().getWarehouses()))
				.isMarketable(isMarketable)
				.build();
	}
	
	/*
	 - Poderia implementar com Hash para contemplar a regra de negócio de identificador único por SKU, 
	 mas pelo cenário ser simples, uma validação no Service não vale esse TradeOff.
	*/
	private static ArrayList<Product> productsArray = new ArrayList<Product>(){{
		add(Product.builder().sku(1L).name("Teste1").inventory(Inventory.builder().quantity(15L).warehouses(Arrays.asList(new Warehouse[] {Warehouse.builder().locality("Warehouse Teste1").quantity(10L).type("Warehouse Teste1").build()})).build()).build());
		add(Product.builder().sku(2L).name("Teste2").inventory(Inventory.builder().quantity(15L).warehouses(Arrays.asList(new Warehouse[] {Warehouse.builder().locality("Warehouse Teste2").quantity(10L).type("Warehouse Teste2").build()})).build()).build());
	}};

}
