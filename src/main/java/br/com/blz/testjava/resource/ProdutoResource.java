package br.com.blz.testjava.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.constant.ConstantsPath;
import br.com.blz.testjava.constant.ConstantsUtil;
import br.com.blz.testjava.dto.ProdutoDTO;
import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.exception.SkuException;
import br.com.blz.testjava.services.ProdutoService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@io.swagger.annotations.Api(value = ConstantsPath.PATH_PRODUTO, produces = MediaType.APPLICATION_JSON_VALUE, tags = { ConstantsUtil.TAG_TRANSFER })
@RestController
@RequestMapping(value = ConstantsPath.PATH_PRODUTO)
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;
	
	/**
	 * 
	 * P2PTransfer.
	 * 
	 * @param 
	 * @return
	 * @throws SkuException 
	 * 
	 * */
	@ResponseBody
	@ApiOperation(value = "Create Produto", response = ProdutoDTO.class)
	@PostMapping
	public Produto createProduto(@RequestBody @Valid ProdutoDTO dto) throws SkuException {
		
		return produtoService.createProduto(dto);
		 
	}
	
	/**
	 * Finds a {@link Produto by its Id.
	 * 
	 * @param FindBySku {@link FindBySKU}
	 * @return {@link ProdutoDTO}
	 * @throws SkuException 
	 * @throws NotFoundException
	 */
	@ResponseBody
    @ApiOperation(value = "Alterar Produto by sku", response = ProdutoDTO.class)
	@PutMapping(value = "/{sku}")
    public Produto atualizarProduto(String sku, @RequestBody @Valid ProdutoDTO dto) throws SkuException {

		return produtoService.atualizarProduto(dto, sku);
		
    }
	
	/**
	 * Finds a {@link Produto by its Id.
	 * 
	 * @param FindBySku {@link FindBySKU}
	 * @return {@link ProdutoDTO}
	 * @throws SkuException 
	 * @throws NotFoundException
	 */
	@ResponseBody
    @ApiOperation(value = "Buscar Produto by sku", response = ProdutoDTO.class)
	@GetMapping(value = "/{sku}")
    public Produto findBySku(String sku) throws SkuException {

		return produtoService.buscarProduto(sku);
		
    }
	
	/**
	 * Finds a {@link Produto by its Id.
	 * 
	 * @param FindBySku {@link FindBySKU}
	 * @return {@link ProdutoDTO}
	 * @throws SkuException 
	 * @throws NotFoundException
	 */
	@ResponseBody
    @ApiOperation(value = "Deletar Produto by sku", response = ProdutoDTO.class)
	@DeleteMapping(value = "/{sku}")
    public void deleteProduto(String sku) throws SkuException {

		produtoService.deleteProduto(sku);
		
    }
}
