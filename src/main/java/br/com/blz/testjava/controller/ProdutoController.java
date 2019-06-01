package br.com.blz.testjava.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.payload.ProdutoPayload;
import br.com.blz.testjava.service.ProdutoException;
import br.com.blz.testjava.service.ProdutoService;


@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	
	@Autowired
	private ProdutoService produtoService;


	/**
	 * salva um produto na base
	 * @throws ProdutoException 
	 */
	@RequestMapping(method=RequestMethod.POST, value="/incluir")
    public String incluirProduto(@RequestBody ProdutoPayload produto) {
		
		try {
			produtoService.incluir(produto);
		} catch (ProdutoException e) {
			return e.getMessage();
		} catch (Exception e) {
			return e.getCause().getCause().getLocalizedMessage();
		}
		
		return "Produto cadastrado com sucesso";
	}

	
	/**
	 * atualiza um produto na base
	 * @throws ProdutoException 
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/atualizar")
    public String atualizarProduto(@RequestBody ProdutoPayload produto) {
		try {
			produtoService.atualizar(produto);
		} catch (ProdutoException e) {
			return e.getMessage();
		} catch (Exception e) {
			return e.getCause().getCause().getLocalizedMessage();
		}
		
		return "Produto atualizado com sucesso";
	}
	
	
	@RequestMapping("/consultar/{sku}")
    //public ProdutoPayload consultarProduto(@PathVariable Integer sku) {
	public String consultarProduto(@PathVariable Integer sku) {
			
		ProdutoPayload pp = null;
		
		try {
			pp = produtoService.consultar(sku);
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pp);
			
			return jsonInString2;
			
		} catch (ProdutoException e) {
			return e.getMessage();	
		} catch (JsonProcessingException e) {
			return e.getMessage();
		} catch (Exception e) {
			return e.getCause().getCause().getLocalizedMessage();
		}
	
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/apagar/{sku}")
    public String apagarProduto(@PathVariable Integer sku) {
		try {
			produtoService.apagar(sku);
		} catch (ProdutoException e) {
			return e.getMessage();		}
		return "O produto foi removido"; 
	}

	
}
