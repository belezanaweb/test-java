package br.com.testeJava.api.services;

import br.com.testeJava.api.documents.Product;

public interface ProductService {

	Product listarPorSku(String sku);
	Product cadastrar(Product product);
	Product atualizar(Product product);
	
	void remover(String sku);
		
}
