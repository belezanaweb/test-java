package br.com.blz.testjava.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Classe que espõe as funcionalidades do produto.
 * @author jmestre
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/blz-java/produto")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;

	@ApiOperation(value = "Salva um produto.")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Produto criado com sucesso."),
		    @ApiResponse(code = 500, message = "Erro genérico!"),
	})
	@PostMapping(path="/", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> salva(@RequestBody Product produto) {
		produtoService.salva(produto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Altera um produto pelo sku.")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Produto alterado com sucesso."),
		    @ApiResponse(code = 404, message = "Produto não encontrado."),
		    @ApiResponse(code = 500, message = "Erro genérico!"),
	})
	@PutMapping(path="/", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> altera(@RequestBody Product produto) {
		produtoService.altera(produto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deleta um produto pelo sku.")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Produto deletado com sucesso."),
		    @ApiResponse(code = 404, message = "Produto não encontrado."),
		    @ApiResponse(code = 500, message = "Erro genérico!"),
	})
	@DeleteMapping(path="/{sku}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> deleta(@PathVariable Integer sku) {
		produtoService.deleta(sku);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Recupera um produto pelo sku.")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Produto recuperado com sucesso."),
		    @ApiResponse(code = 404, message = "Produto não encontrado."),
		    @ApiResponse(code = 500, message = "Erro genérico!"),
	})
	@GetMapping(path="/{sku}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> recupera(@PathVariable Integer sku) {
		Product produto = produtoService.recupera(sku);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}
}
