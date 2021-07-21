package br.com.blz.testjava.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.controller.form.ProdutoForm;
import br.com.blz.testjava.exceptions.ProductAlredyExistsException;
import br.com.blz.testjava.model.Mensagem;
import br.com.blz.testjava.model.Produto;

@RestController
@RequestMapping("/produtos")
public class BelezaController {

	private static HashMap<Long, Produto> produtos = new HashMap<Long, Produto>();
	public static final String NOT_FOUND = "Item não encontrado";
	public static final int COD_001 = 001;

	@PostMapping("/")
	public ResponseEntity<Produto> createItem(@RequestBody ProdutoForm produto) throws Exception {

		Produto prod = produto.converterProduto();
		if (produtos.containsKey(prod.getSku())) {
			throw (new ProductAlredyExistsException());
		}
		produtos.put(prod.getSku(), prod);

		return ResponseEntity.ok(prod);
	}

	@GetMapping("/{sku}")
	public ResponseEntity<?> getItem(@PathVariable long sku) {

		if (produtos.get(sku) != null) {
			return ResponseEntity.ok(produtos.get(sku));
		} else {
			Mensagem msg = new Mensagem(COD_001, NOT_FOUND);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(msg);
		}
	}

	@PutMapping("/{sku}")
	@ResponseBody
	public ResponseEntity<?> atualizaItem(@PathVariable long sku, @RequestBody ProdutoForm produto) {

		if (produtos.get(sku) != null) {
			Produto prod = produto.converterProduto();
			if (prod.getSku() != sku) {
				produtos.remove(sku);	
			}
				produtos.put(prod.getSku(), prod);
			
			return ResponseEntity.ok(prod);
		} else {
			Mensagem msg = new Mensagem(COD_001, NOT_FOUND);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(msg);
		}
	}

	@DeleteMapping("/{sku}")
	@ResponseBody
	public ResponseEntity<?> removeItem(@PathVariable long sku) {

		Produto prod = produtos.get(sku);
		if (prod != null) {

			produtos.remove(sku);
			return ResponseEntity.status(HttpStatus.OK).body(prod);
		} else {
			Mensagem msg = new Mensagem(COD_001, NOT_FOUND);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(msg);
		}
	}

}
