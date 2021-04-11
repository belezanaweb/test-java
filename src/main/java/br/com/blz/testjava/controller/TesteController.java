package br.com.blz.testjava.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.api.model.ProdutoModel;
import br.com.blz.testjava.api.model.convert.ProdutoModelConvert;
import br.com.blz.testjava.api.model.input.ProdutoInput;
import br.com.blz.testjava.domain.repository.ProdutoRepository;
import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.service.CadastroProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class TesteController {

	@Autowired
	private ProdutoRepository repository;
	@Autowired
	private CadastroProdutoService produtoService;
	@Autowired
	private ProdutoModelConvert modelConvert;

	@GetMapping
	public ResponseEntity<List<ProdutoModel>> getProdutos() {
		return ResponseEntity.ok(modelConvert.toCollectionModel(repository.findAll()));
	}

	@GetMapping("/{sku}")
	public ProdutoModel buscar(@PathVariable Long sku) {
		return modelConvert.toModel(produtoService.buscarOuFalhar(sku));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@RequestBody @Valid ProdutoInput produtoInput) {
		return modelConvert.toModel(produtoService.salvar(modelConvert.fromModel(produtoInput)));
	}

	@PutMapping("/{sku}")
	public ProdutoModel atualizar(@PathVariable Long sku, @RequestBody @Valid ProdutoInput skuInput) {
		Produto produtoAtual = produtoService.buscarOuFalhar(sku);
		modelConvert.copyToDomainObject(skuInput, produtoAtual);

		return modelConvert.toModel(produtoService.atualizar(produtoAtual));
	}

	@DeleteMapping("/{sku}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long sku) {
		produtoService.excluir(sku);
	}
}
