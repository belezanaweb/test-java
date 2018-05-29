package br.com.blz.testjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Product;

@RestController
@RequestMapping(value = "/produto")
public class EndPoint {

	private List<Product> produtosCadastrados = new ArrayList<Product>();

	@RequestMapping(value = "/{sku}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findProduct(@PathVariable("sku") int sku) {
		Product findedProduct = new Product();
		findedProduct.setSku(sku);
		findedProduct = findProduct(findedProduct);
		if (findedProduct == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensagem", "Produto Informado não existente"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(findedProduct);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		if (findProduct(product) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensagem", "Produto Informado ja se encontra cadastrado"));
		}
		produtosCadastrados.add(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editProduct(@RequestBody Product product) {
		Product findedProduct = findProduct(product);
		if (findedProduct == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensagem", "Produto Informado não existente"));
		}
		produtosCadastrados.removeIf(produtoList -> produtoList.getSku().equals(product.getSku()));
		produtosCadastrados.add(product);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}

	@RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteProduct(@RequestBody Product product) {
		Product findedProduct = findProduct(product);
		if (findedProduct == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensagem", "Produto Informado não existente"));
		}
		produtosCadastrados.removeIf(produtoList -> produtoList.getSku().equals(product.getSku()));
		return ResponseEntity.status(HttpStatus.OK)
				.body(Collections.singletonMap("mensagem", "Produto Informado foi excluido com sucesso"));
	}

	private Product findProduct(Product product) {
		return this.produtosCadastrados.stream().filter(listProducts -> listProducts.getSku().equals(product.getSku()))
				.findAny().orElse(null);
	}

}
