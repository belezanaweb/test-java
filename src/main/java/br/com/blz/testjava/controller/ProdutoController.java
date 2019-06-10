package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.service.ProdutoService;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void novoProduto(@RequestBody Produto produto) throws Exception{		
		produtoService.addProduto(produto);
	}
		
	@RequestMapping(method = RequestMethod.GET)
	public Produto recuperaProduto(@RequestParam(value = "sku") Long sku) throws Exception{		
		return  produtoService.getProduto(sku); 		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteProduto(@RequestParam(value = "sku") Long sku) throws Exception{	
		produtoService.delProduto(sku);		
	}
			
	@RequestMapping(method = RequestMethod.PUT)
	public void atualizaProduto(@RequestBody Produto produto) throws Exception{		
		produtoService.updateProduto(produto);		
	}
	
	
	
}
