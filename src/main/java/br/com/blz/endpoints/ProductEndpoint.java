package br.com.blz.endpoints;

import java.util.List;

import javax.validation.Valid;

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

import br.com.blz.models.ProductEntity;
import br.com.blz.repository.ProductRepository;
import ch.qos.logback.core.status.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Produtos")
public class ProductEndpoint {

	@Autowired
	private ProductRepository productDAO;

	@ApiOperation(value = "Salva um produto")
	@PostMapping("/product")
	public ResponseEntity<Object> salvaProduto(@RequestBody @Valid ProductEntity productEntity) {
		
		if (productDAO.existsById(productEntity.getSku()))
			return new ResponseEntity<Object>("Produto com sku: " + productEntity.getSku() + " já cadastrado",
					HttpStatus.FORBIDDEN);
		return new ResponseEntity<Object>(productDAO.save(productEntity), HttpStatus.OK);
	}

	@ApiOperation(value = "Teste Salvar um produto")
	@PostMapping("/teste")
	public ProductEntity salvaProduto() {
		return productDAO.save(Teste.getClienteTeste());
	}

	@ApiOperation(value = "Deleção de produto por sku")
	@DeleteMapping("/product/{sku}")
	public void deletaProduto(@PathVariable(value = "sku") long sku) {
		productDAO.deleteById(sku);
	}

	@ApiOperation(value = "Atualiza um produto")
	@PutMapping("/product")
	public ProductEntity atualizaProduto(@RequestBody @Valid ProductEntity productEntity) {

		ProductEntity produtoAtualizado = productDAO.findById(productEntity.getSku());
		produtoAtualizado.setName(productEntity.getName());
		produtoAtualizado.setInventory(productEntity.getInventory());
		return productDAO.save(produtoAtualizado);
	}

	@ApiOperation(value = "Retorna uma lista de Produtos")
	@GetMapping("/products")
	public List<ProductEntity> listaProdutos() {
		return productDAO.findAll();
	}

	@ApiOperation(value = "Recuperação de produto por sku")
	@GetMapping("/product/{sku}")
	public ResponseEntity<Object> listaProdutoUnico(@PathVariable(value = "sku") long sku) {

		ProductEntity productEntity = productDAO.findById(sku);
		if (productEntity != null)
			return new ResponseEntity<Object>(productEntity, HttpStatus.OK);

		return new ResponseEntity<Object>("Não encontrado produto" + sku, HttpStatus.NOT_FOUND);

	}

}
