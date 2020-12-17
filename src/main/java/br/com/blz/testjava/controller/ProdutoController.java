package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.models.Produto;
import br.com.blz.testjava.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Api(value="API REST Produtos")
public class ProdutoController {

	@Autowired
	ProdutoService produtoRepository;
	
	@GetMapping("/teste")
	public String index(){
		return "Greetings from Spring Boot!";
	}
	
	@ApiOperation(value="Retorna a lista de todos os produtos")
	@GetMapping("/produtos")
	public ResponseEntity<Object> listaProdutos(){
		return new ResponseEntity<>(produtoRepository.findAll(),HttpStatus.OK);
	}
	
	@ApiOperation(value="Retorna um produto unico")
	@GetMapping("/produto/{id}")
	public ResponseEntity<Object> listaProdutoUnico(@PathVariable(value="id") long id){
		Produto p = null;
		if( (p = produtoRepository.findById(id)) == null ) {
			return  new ResponseEntity<Object>("Produto nao encontrado para o sku:"+id, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(p,HttpStatus.OK);
	}
	
	@ApiOperation(value="Salva um produto")
	@PostMapping("/produto")
	public ResponseEntity<Object> salvaProduto(@RequestBody Produto produto) throws Exception {
		if( (produtoRepository.findById(produto.getSku())) != null ) {
			throw new Exception("Produto ja existente para o sku:"+produto.getSku());
		}
		Produto p = produtoRepository.save(produto);
		return new ResponseEntity<>(p,HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Deleta um produto")
	@DeleteMapping("/produto")
	public ResponseEntity<Object> deletaProduto(@RequestBody Produto produto) {
		if( (produtoRepository.findById(produto.getSku())) == null ) {
			return  new ResponseEntity<Object>("Produto nao encontrado para o sku:"+produto.getSku(), HttpStatus.BAD_REQUEST);
		}
		produtoRepository.delete(produto);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(value="Atualiza um produto")
	@PutMapping("/produto")
	public ResponseEntity<Object> atualizaProduto(@RequestBody Produto produto) {
		if( (produtoRepository.findById(produto.getSku())) == null ) {
			return  new ResponseEntity<Object>("Produto nao encontrado para o sku:"+produto.getSku(), HttpStatus.BAD_REQUEST);
		}
		Produto p = produtoRepository.save(produto);
		return new ResponseEntity<>(p,HttpStatus.OK);
	}
	
}
