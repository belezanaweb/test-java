package br.com.blz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.exception.BlzProductExistsException;
import br.com.blz.exception.BlzProductNotFoundException;
import br.com.blz.model.InventoryModel;
import br.com.blz.model.ProductModel;
import br.com.blz.repository.ProductRepository;

/**
 * Classe de serviço, onde as regras de negócio são executadas
 *  
 * @author tiago
 */
@Service
public class ProductService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductRepository repository;
	
	/**
	 * Este método salva o produto na base de dados
	 * 
	 * @param model
	 * @return ProductModel
	 * @throws BlzProductExistsException
	 */
	public ProductModel save(ProductModel model) throws BlzProductExistsException {
		logger.debug("Executando o save no service");
		
		return this.repository.save(model);
	}
	
	/**
	 * Este método atualiza o produto na base de dados
	 * 
	 * @param model
	 * @return ProductModel
	 * @throws BlzProductNotFoundException
	 */
	public ProductModel update(ProductModel model) throws BlzProductNotFoundException {
		logger.debug("Executando o update no service");
		
		return this.repository.update(model);
	}

	/**
	 * Este método recupera o produto da base de dados pelo sku recebido
	 * 
	 * @param sku
	 * @return ProductModel
	 * @throws BlzProductNotFoundException
	 */
	public ProductModel findBySku(Integer sku) throws BlzProductNotFoundException {
		logger.debug("Executando o findBySku no service");
		
		ProductModel model = this.repository.findBySku(sku);
		this.updateQuantityAndMarketable(model);
		
		return model;
	}
	
	/**
	 * Apagando da base de dados o produto cujo sku informado
	 * 
	 * @param sku
	 * @return boolean
	 * @throws BlzProductNotFoundException
	 */
	public boolean delete(Integer sku) throws BlzProductNotFoundException {
		logger.debug("Executando o delete no service");
		
		return this.repository.delete(sku);
	}
	
	/**
	 * Atualizando a informação de quantidade e marketable
	 * 
	 * @param product
	 */
	private void updateQuantityAndMarketable(ProductModel product) {
		InventoryModel inventory = product.getInventory();
		
		int qtd = inventory.getWarehouses().stream().mapToInt(warehouse -> warehouse.getQuantity()).sum();
		
		if(qtd > 0) {
			product.setMarketable(true);
		}
		
		inventory.setQuantity(qtd);
	}
}