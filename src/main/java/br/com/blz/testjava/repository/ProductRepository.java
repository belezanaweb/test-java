package br.com.blz.testjava.repository;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.blz.testjava.dto.ProductDTO;

@Component
@Scope("singleton")
public class ProductRepository {

	HashMap<Integer, ProductDTO> list;

	public ProductRepository() {
		list = new HashMap<Integer, ProductDTO>();
	}

	public ProductDTO getProduct(int sku) {
		return list.get(sku);
	}

	public ProductDTO deleteProduct(int sku) {
		return list.remove(sku);
	}

	public ProductDTO createProduct(ProductDTO product) throws Exception {
		if (list.containsKey(product.getSku())) {
			// Caso um produto já existente em memória tente ser criado com o mesmo sku uma
			// exceção deverá ser lançada
			throw new Exception("Product Already exists", null);
		}
		return list.put(product.getSku(), product);
	}

	public ProductDTO updateProduct(ProductDTO product) {
		return list.replace(product.getSku(), product);
	}
}
