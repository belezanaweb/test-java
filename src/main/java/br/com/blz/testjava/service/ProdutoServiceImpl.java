package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouses;
import br.com.blz.testjava.repository.ProdutoRepository;

/**
 * Classe que contem as regras de negócio referente a produto.
 * @author jmestre
 */
@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	/**
	 * Método que realiza a lógica para salvar um produto.
	 */
	@Override
	public void salva(Product produto) {
		if (repository.find(produto.getSku()) != null) {
			throw new IllegalArgumentException("Já existe um produto com o mesmo sku.");
		}
		repository.save(produto);
	}

	/**
	 * Método que realiza a lógica para alterar um produto.
	 */
	@Override
	public void altera(Product produto) {
		deleta(produto.getSku());
		repository.update(produto);
	}

	/**
	 * Método que realiza a lógica para deletar um produto.
	 */
	@Override
	public void deleta(Integer sku) {
		repository.delete(sku);
	}

	/**
	 * Método que realiza a lógica para recuperar um produto.
	 */
	@Override
	public Product recupera(Integer sku) {
		Product produto = repository.find(sku);
		
		if (produto == null) {
			return null;
		}
		
		calculateInventoryQuantity(produto);
		calculateMarketable(produto);
		return produto;
	}

	/**
	 * Método que calcula a quantidade de inventário do produto.
	 * @param produto
	 */
	private void calculateInventoryQuantity(Product produto) {
		Integer inventoryQuantity = 0;
		for (Warehouses warehouse : produto.getInventory().getWarehouses()) {
			inventoryQuantity += warehouse.getQuantity(); 
		}
		produto.getInventory().setQuantity(inventoryQuantity);
	}

	/**
	 * Método que verifica se o produto é comercializável.
	 * @param produto
	 */
	private void calculateMarketable(Product produto) {
		if (produto.getInventory() != null && produto.getInventory().getQuantity() > 0) {
			produto.setIsMarketable(Boolean.TRUE);
		}
	}
}
