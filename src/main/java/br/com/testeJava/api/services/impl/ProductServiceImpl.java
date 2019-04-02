package br.com.testeJava.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.testeJava.api.documents.Product;
import br.com.testeJava.api.documents.Warehouse;
import br.com.testeJava.api.repositories.ProductRepository;
import br.com.testeJava.api.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product listarPorSku(String sku) {
		Product productLoc = productRepository.findBySku(sku);
		Integer somaWarehouses=0;
		if(null!=productLoc) {
			productLoc.getInventory().setQuantity(0);
			somaWarehouses=getSomaWarehouses(productLoc.getInventory().getWarehouses()); 
			productLoc.getInventory().setQuantity(somaWarehouses);
			if(productLoc.getInventory().getQuantity().equals(0)) {
				productLoc.setIsMarketable(true);
			}
			else {
				productLoc.setIsMarketable(false);
			}
		}
		return productLoc;
	}

	private Integer getSomaWarehouses(List<Warehouse> warehouse) {
		Integer qtdSoma=0;
		if(null!=warehouse) {
			for (Warehouse item : warehouse) {
				qtdSoma+=item.getQuantity();
			}
		}	
		return qtdSoma;
	}

	@Override
	public Product atualizar(Product product) {
		this.productRepository.save(product);
		return null;
	}

	@Override
	public void remover(String sku) {
		this.productRepository.deleteById(sku);

	}

	@Override
	public Product cadastrar(Product product) {
		return this.productRepository.save(product);
	}

}
