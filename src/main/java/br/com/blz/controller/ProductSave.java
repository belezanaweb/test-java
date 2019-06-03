package br.com.blz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.blz.adapter.ProductAdapter;
import br.com.blz.exception.BlzProductExistsException;
import br.com.blz.exception.BlzProductNotFoundException;
import br.com.blz.model.ProductModel;
import br.com.blz.service.ProductService;

/**
 * Classe controller para o fluxo de produto
 *  
 * @author tiago
 */
@Controller
@RequestMapping("blz/product/v1")
public class ProductSave {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductService service;
	
	/**
	 * Este método insere o produto na base de dados se este não existir.
	 * Caso seja encontrado um produto de mesmo sku, a exception BlzProductExistsException é lançada.
	 * 
	 * @param entrada
	 * @return String
	 * @throws BlzProductExistsException
	 */
	@RequestMapping(value="/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
	@ResponseBody
	public String save(@RequestBody final Object entrada) throws BlzProductExistsException {
		
		logger.debug("Executando o método save do Controller");
		
		ProductModel model = ProductAdapter.toModel(entrada);
		service.save(model);
		
		return this.getJson(model);
	}
	
	/**
	 * Este método atualiza o produto na base de dados se este não existir.
	 * Caso não seja encontrado um produto de mesmo sku, a exception BlzProductNotFoundException é lançada.
	 * 
	 * @param entrada
	 * @return String
	 * @throws BlzProductNotFoundException
	 */
	@RequestMapping(value="/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String update(@RequestBody final Object entrada) throws BlzProductNotFoundException {
		
		logger.debug("Executando o método update do Controller");
		
		ProductModel model = ProductAdapter.toModel(entrada);
		service.update(model);
		
		return this.getJson(model);
	}
	
	/**
	 * Este método efetua a busca do produto pelo sku informado
	 * 
	 * @param sku
	 * @return String
	 * @throws BlzProductNotFoundException
	 */
	@RequestMapping(value="/read", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public String read(@RequestParam final String sku) throws BlzProductNotFoundException {
		
		logger.debug("Executando o método read do Controller");
		
		ProductModel model = service.findBySku(new Integer(sku));
		
		return this.getJson(model);
	}
	
	/**
	 * Apagando o produto da base de dados
	 * 
	 * @param sku
	 * @return String
	 * @throws BlzProductNotFoundException
	 */
	@RequestMapping(value="/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@RequestParam final String sku) throws BlzProductNotFoundException {
		logger.debug("Executando o método delete do Controller");
		
		if(service.delete(new Integer(sku))) {
			return "{\"success\": \"Produto de sku " + sku + " excluído com sucesso\"}";
		}
		
		return "{\"error\": \"Erro ao excluir o produto de sku " + sku + "\"}";
	}
	
	/**
	 * Transformando o objeto em json
	 * 
	 * @param model
	 * @return String
	 */
	private String getJson(ProductModel model) {
		Gson gson = new Gson();
		return gson.toJson(model);
	}
	
}