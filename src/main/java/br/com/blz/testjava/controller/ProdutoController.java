package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.ProdutoSalvamentoDTO;
import br.com.blz.testjava.model.exception.ExistingProductException;
import br.com.blz.testjava.model.exception.ObjectNotFoundException;
import br.com.blz.testjava.service.ProdutoService;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody ProdutoSalvamentoDTO produto) throws ExistingProductException {

		return new ResponseEntity<>(service.save(produto), HttpStatus.CREATED);

	}

	@GetMapping("/{sku}")
	public ResponseEntity<?> findBySku(@PathVariable Long sku) throws ObjectNotFoundException {

		return new ResponseEntity<>(service.findBySku(sku), HttpStatus.OK);
	}

	@DeleteMapping("/{sku}")
	public ResponseEntity<?> delete(@PathVariable Long sku) {
		service.delete(sku);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
