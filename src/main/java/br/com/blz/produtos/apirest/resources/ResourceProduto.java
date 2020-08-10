package br.com.blz.produtos.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.com.blz.produtos.apirest.error.ApiRequestException;
import br.com.blz.produtos.apirest.repository.RepositoryProduto;
import br.com.blz.produtos.apirest.repository.RepositoryWarehouse;
import br.com.blz.produtos.apirest.view.InserirProdutoViewProdutoRequest;
import br.com.blz.produtos.apirest.view.ListarProdutosViewResponse;
import br.com.blz.produtos.apirest.view.ObterProdutoViewProdutoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Produtos")
@CrossOrigin(origins="*")
public class ResourceProduto {

	@Autowired(required = true)
	//@org.springframework.beans.factory.annotation.Autowired(required=true)
	RepositoryProduto produtoRepository;
	
	@Autowired(required = true)
	RepositoryWarehouse warehouseRepository;
	
	
	@GetMapping("/produto/{sku}")
	@ApiOperation(value="Retorna um produto")
	@ResponseStatus(HttpStatus.OK)
	public ObterProdutoViewProdutoResponse obterProduto(@PathVariable(value="sku") long sku){
		
		//Possível lançar erro quando o produto não esta cadastrado
		//Se não lançar erro vai retornar o objeto vazio {}
		
		//if(!produtoRepository.existsById(sku)){
		//	throw new ApiRequestException("Produto não encontrado",HttpStatus.BAD_REQUEST);
		//} 
		
		return new ObterProdutoViewProdutoResponse(produtoRepository.findById(sku));
	}
	
	@GetMapping("/produtos")
	@ApiOperation(value="Retorna uma lista de produtos")
	@ResponseStatus(HttpStatus.OK)
	public List<ObterProdutoViewProdutoResponse> listarProdutos(){
		return new ListarProdutosViewResponse(produtoRepository.findAll()).getObterProdutoViewProdutosResponse();
	}
	
	@DeleteMapping("/produto/{sku}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="Apaga um produto")
	public void removerProduto(@PathVariable(value="sku") long sku){
		
		if(!produtoRepository.existsById(sku)){
			throw new ApiRequestException("Produto desconhecido",HttpStatus.BAD_REQUEST);
		}
		
		produtoRepository.deleteById(sku);
	}

	
	@PostMapping("/produto")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Cadastra um produto")
	public ObterProdutoViewProdutoResponse inserirProduto(@Valid @RequestBody InserirProdutoViewProdutoRequest produto){
		
		if(produtoRepository.existsById(produto.getSku())){
			throw new ApiRequestException("Produto existente",HttpStatus.BAD_REQUEST);
		} 
		
		return new ObterProdutoViewProdutoResponse(produtoRepository.save(produto.getEntityProduto()));
		
	}
	
		
	@PutMapping("/produto")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value="Atualiza um produto")
	public ObterProdutoViewProdutoResponse atualizarProduto(@RequestBody InserirProdutoViewProdutoRequest produto){
		
		if(!produtoRepository.existsById(produto.getSku())){
			throw new ApiRequestException("Produto desconhecido",HttpStatus.BAD_REQUEST);
		}
		
		return new ObterProdutoViewProdutoResponse(produtoRepository.save(produto.getEntityProduto()));
	}
	
}
