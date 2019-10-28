package br.com.blz.testjava.resource;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dao.entity.Product;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.dto.ProductRespDto;
import br.com.blz.testjava.service.IProductService;
import br.com.blz.testjava.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Product API")
@RequestMapping("/api/products")
public class ProductResource {

	@Autowired
    private IProductService productService;
	
	 @Autowired
	 private ModelMapper modelMapper;

	@ApiOperation(value = "View a list of available products", response = ProductDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    @GetMapping
    public List<ProductRespDto> findAll() {
        Iterable<Product> products = productService.findAll();
        return StreamSupport.stream(products.spliterator(), false)
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }
    
    @GetMapping(value = "/{sku}")
    public ProductRespDto find(@PathVariable("sku") Long sku) {
    	return convertToDto(productService.findById(sku));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductRespDto create(@RequestBody ProductDto resource) throws ParseException {
    	Product product = convertToEntity(resource);
        Product productPostCreated = productService.create(product);
        return convertToDto(productPostCreated);
    }
    
    @PutMapping(value = "/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public ProductRespDto update(@PathVariable("sku") Long sku, @RequestBody ProductDto resource) throws ParseException {
    	Product product = convertToEntity(resource);
        Product productPostCreated = productService.update(sku, product);
        return convertToDto(productPostCreated);
    }

    @DeleteMapping(value = "/{sku}")
    public void delete(@PathVariable("sku") Long sku) {
       productService.delete(sku);
    }

    private ProductRespDto convertToDto(Product Product) {
    	ProductRespDto productRespDto = modelMapper.map(Product, ProductRespDto.class);
        return productRespDto;
    }
    
    private Product convertToEntity(ProductDto productDto) throws ParseException {
        Product product = modelMapper.map(productDto, Product.class);
        product.setSku(ProductService.buildProdSku(productDto.getSku()));
        return product;
    }    
}