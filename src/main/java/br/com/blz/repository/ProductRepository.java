package br.com.blz.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.blz.exception.BlzProductExistsException;
import br.com.blz.exception.BlzProductNotFoundException;
import br.com.blz.model.InventoryModel;
import br.com.blz.model.ProductModel;
import br.com.blz.model.WarehouseModel;

@Service/* Aqui deveríamos ter uma interface. Como criei uma classe, tive que anota-la para a injeção*/
public class ProductRepository {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private List<ProductModel> products = new ArrayList<>();
	
	/**
	 * Este trecho de código cria a base de dados inicial em memória para o teste
	 */
	{
		/* Produto A */
		List<WarehouseModel> warehouses = new ArrayList<>();
		warehouses.add(new WarehouseModel("SP", 250, "ECOMMERCE"));
		warehouses.add(new WarehouseModel("SC", 50, "ECOMMERCE"));
		warehouses.add(new WarehouseModel("MOEMA", 145, "PHYSICAL_STORE"));
		
		InventoryModel inventory = new InventoryModel(2, warehouses);
		
		ProductModel pA = new ProductModel(12345, "Camiseta branca", inventory);
		
		/* Produto B */
		List<WarehouseModel> warehousesB = new ArrayList<>();
		warehousesB.add(new WarehouseModel("RJ", 5, "ECOMMERCE"));
		warehousesB.add(new WarehouseModel("SP", 15, "ECOMMERCE"));
		warehousesB.add(new WarehouseModel("MOEMA", 1045, "PHYSICAL_STORE"));
		
		InventoryModel inventoryB = new InventoryModel(2, warehousesB);
		
		ProductModel pB = new ProductModel(6789, "Camiseta vermelha", inventoryB);
		
		this.products.add(pA);
		this.products.add(pB);
	}

	/**
	 * Salva o produto na base de dados
	 * 
	 * @param model
	 * @return ProductModel
	 * @throws BlzProductExistsException
	 */
	public ProductModel save(ProductModel model) throws BlzProductExistsException {
		
		if(products.contains(model)) {
			throw new BlzProductExistsException("O produto " + model.getSku() + " já existe em nossa base de dados");
		}
		
		this.products.add(model);
		
		logger.debug("Sku " + model.getSku() + " salvo com sucesso");
		
		return model;
	}

	/**
	 * Atualiza o produto na base de dados
	 * 
	 * @param model
	 * @return ProductModel
	 * @throws BlzProductNotFoundException
	 */
	public ProductModel update(ProductModel model) throws BlzProductNotFoundException {
		
		if(!products.contains(model)) {
			throw new BlzProductNotFoundException("O produto " + model.getSku() + " não existe em nossa base de dados");
		}
		
		ProductModel pm = this.findBySku(model.getSku());
		pm.setName(model.getName());
		
		logger.debug("Sku " + model.getSku() + " atualizado com sucesso");
		
		return model;
	}
	
	/**
	 * Recupera o produto pelo seu sku
	 * 
	 * @param sku
	 * @return ProductModel
	 * @throws BlzProductNotFoundException
	 */
	public ProductModel findBySku(Integer sku) throws BlzProductNotFoundException {
		
		ProductModel pm = null;
		
		pm = this.products.stream()
		   .filter(p -> p.getSku().equals(sku))
		   .findFirst().orElse(null);
		   
		if(pm == null) {
			throw new BlzProductNotFoundException("Não encontrado o produto de sku " + sku);
		}
		
		return pm;
	}
	
	/**
	 * Apaga o produto de sku fornecido
	 * 
	 * @param sku
	 * @return boolean
	 * @throws BlzProductNotFoundException
	 */
	public boolean delete(Integer sku) throws BlzProductNotFoundException {
		ProductModel pm = this.findBySku(sku);
		
		boolean b = this.products.remove(pm);
		
		return b;
	}
}