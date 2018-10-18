package br.com.blz.testjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dominio.Product;
import br.com.blz.testjava.services.exceptions.DataIntegrityException;
import br.com.blz.testjava.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductDataService dataService;
	
	public Product findBySku(Long sku) {		
		
		Product obj = dataService.findBySku(sku);
		
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Produto não encontrado! sku: " + sku + ", Tipo: " + Product.class.getName());
		}
		
		return obj;
		
	}
	
	public List<Product> findAll() {		
		return dataService.findAll();
	}
	
	public Product insert(Product product) {
		Product obj = null;
		obj = dataService.findBySku(product.getSku());
		if (obj != null) {
			throw new DataIntegrityException(
					"Produto já cadastrado! sku: " + obj.getSku() + ", Tipo: " + Product.class.getName());
		}
		
		return dataService.save(product);
	}
	
	public Product update(Product product) {
		findBySku(product.getSku()); // Apenas para verificar se o produto existe e estourar uma exceção caso não exista.		
		
		//Tratamento para não atualizar o objeto vazio e depois dar um erro no
		//momento da requisição get by sku.
		if (product.getInventory() == null) {
			throw new DataIntegrityException(
					"O Inventory do produto precisa ser informado. Tipo: " + Product.class.getName());
		} else if (product.getInventory().getWareHouses() == null) {
			throw new DataIntegrityException(
					"O WareHouse do Inventory ser informado. Tipo: " + Product.class.getName());
		}
		
		return dataService.save(product);
	}
	
	public void delete(Long sku) {
		findBySku(sku); // Apenas para verificar se o produto existe e estourar uma exceção caso não exista.
		dataService.delete(sku);
	}
	
}
