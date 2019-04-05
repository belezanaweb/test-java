package br.com.blz.testjava.resource;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.constants.ConstantsPath;
import br.com.blz.testjava.constants.ConstantsUtil;
import br.com.blz.testjava.dto.ProdutoDTO;
import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.exception.SkuException;
import br.com.blz.testjava.service.ProdutoService;
import io.swagger.annotations.ApiOperation;

@io.swagger.annotations.Api(value = ConstantsPath.PATH_PRODUTO, produces = MediaType.APPLICATION_JSON_VALUE, tags = { ConstantsUtil.TAG_TRANSFER })
@RestController
@RequestMapping(value = ConstantsPath.PATH_PRODUTO)
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;

	
	/**
	 * 
	 * Produto.
	 * 
	 * @param produto
	 * @return
	 * @throws SkuException 
	 * 
	 * */
	@ResponseBody
	@ApiOperation(value = "produto", response = ProdutoDTO.class)
	@PostMapping("/")
	public Produto produto(@RequestBody @Valid ProdutoDTO dto) throws SkuException {
		
		return service.createProduto(dto);
		 
	}
	
	/**
	 * Finds a {@link Produto by its Id.
	 * 
	 * @param FindBySku {@link FindBySKU}
	 * @return {@link ProdutoDTO}
	 * @throws NotFoundException
	 */
	@ResponseBody
    @ApiOperation(value = "Find Produto by sku", response = ProdutoDTO.class)
	@PutMapping(value = "/{sku}")
    public Produto atualizarProduto(ProdutoDTO dto, String sku) {

		return service.atualizarProduto(dto, sku);
		
    }
	
	/**
	 * Finds a {@link Produto by its Id.
	 * 
	 * @param FindBySku {@link FindBySKU}
	 * @return {@link ProdutoDTO}
	 * @throws NotFoundException
	 */
	@ResponseBody
    @ApiOperation(value = "Find Produto by sku", response = ProdutoDTO.class)
	@GetMapping(value = "/{sku}")
    public Produto findBySku(String sku) {

		return service.findBySku(sku);
		
    }
	
	/**
	 * Finds a {@link Produto by its Id.
	 * 
	 * @param FindBySku {@link FindBySKU}
	 * @return {@link ProdutoDTO}
	 * @throws NotFoundException
	 */
	@ResponseBody
    @ApiOperation(value = "Find Produto by sku", response = ProdutoDTO.class)
	@DeleteMapping(value = "/{sku}")
    public void deleteProduto(String sku) {

		service.deleteProduto(sku);
		
    }
}
