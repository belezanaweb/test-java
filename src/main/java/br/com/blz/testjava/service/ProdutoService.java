package br.com.blz.testjava.service;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Product;

/**
 * Interface do serviço de produto.
 * @author jmestre
 */
@Service
public interface ProdutoService {

	public void salva(Product produto);
	
	public void altera(Product produto);
	
	public void deleta(Integer sku);
	
	public Product recupera(Integer sku);
}
