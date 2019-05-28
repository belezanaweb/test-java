package br.com.blz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.business.BelezaBO;
import br.com.blz.model.ProdutoVO;
import br.com.blz.model.RetornoProdutoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/beleza")
@Api(tags= {"Beleza"}, description="CRUD de produto de beleza")
@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Sucesso"),
		@ApiResponse(code = 400, message = "Parâmetro inválido"),
		@ApiResponse(code = 403, message = "Acesso negado"),
		@ApiResponse(code = 404, message = "Registro não encontrado")
})
public class BelezaController {
	
	@Autowired
	private BelezaBO belezaBO;
	
	
	@ApiOperation(value = "Cadastrar produto", response=ProdutoVO.class)
	@PostMapping(value = "/produto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cadastrarProduto(@RequestBody ProdutoVO produto) {
		return belezaBO.cadastrarProduto(produto);
	}
	
	@ApiOperation(value = "Atualizar produto a partir dos SKU informado", response=ProdutoVO.class)
	@PutMapping(value = "/produto/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editarProduto(@RequestBody ProdutoVO produto) {
		return belezaBO.editarProduto(produto);
	}
	
	@ApiOperation(value = "Obter produto a partir dos SKU informado", response=ProdutoVO.class)
	@GetMapping(value = "/produto/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RetornoProdutoVO> consutarProduto(@PathVariable("sku") Integer sku) {
		return belezaBO.obterProdutoPorSku(sku);
	}
	
	@ApiOperation(value = "Excluir produto a partir dos SKU informado", response=ProdutoVO.class)
	@DeleteMapping(value = "/produto/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deletarProduto(@PathVariable("sku") Integer sku) {
		return belezaBO.deletarProdutoPorSku(sku);
	}
}
