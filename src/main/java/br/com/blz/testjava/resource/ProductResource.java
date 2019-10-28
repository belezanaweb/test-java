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
import br.com.blz.testjava.dao.entity.ProductEntryPK;
import br.com.blz.testjava.dao.repository.IProductRepository;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.exception.ProductSKUMismatchException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Product API")
@RequestMapping("/api/products")
public class ProductResource {

	@Autowired
    private IProductRepository productRepository;
	
	 @Autowired
	 private ModelMapper modelMapper;

	@ApiOperation(value = "View a list of available products", response = ProductDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public List<ProductDto> findAll() {
        Iterable<Product> products = productRepository.findAll();
        return StreamSupport.stream(products.spliterator(), false)
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }
    
    @GetMapping(value = "/{sku}")
    public ProductDto findById(@PathVariable("sku") Long sku) {
    	return convertToDto(productRepository.findById(buildProdSku(sku))
    			.orElseThrow(ProductNotFoundException::new));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody ProductDto resource) throws ParseException {
    	if (null == resource.getSku()){
            throw new IllegalArgumentException("Sku is requerid.");
    	}
    	
    	if (productRepository.findById(buildProdSku(resource.getSku())).isPresent()) {
            throw new IllegalArgumentException("Product Already Exists");
    	}
    	Product product = convertToEntity(resource);
        Product productPostCreated = productRepository.save(product);
        return convertToDto(productPostCreated);
    }

	private ProductEntryPK buildProdSku(Long sku) {
		ProductEntryPK productSku = new ProductEntryPK();
    	productSku.setSku(sku);
    	return productSku;
	}
    
    @PutMapping(value = "/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto update(@PathVariable("sku") Long sku, @RequestBody ProductDto resource) throws ParseException {
    	if (!resource.getSku().equals(sku)) {
             throw new ProductSKUMismatchException("Product SKU mismatch.");
        }
    	productRepository.findById(buildProdSku(sku))
        .orElseThrow(ProductNotFoundException::new);
    	
    	Product product = convertToEntity(resource);
        Product productPostCreated = productRepository.save(product);
        return convertToDto(productPostCreated);
    }

    @DeleteMapping(value = "/{sku}")
    public void delete(@PathVariable("sku") Long sku) {
    	 productRepository.findById(buildProdSku(sku))
         .orElseThrow(ProductNotFoundException::new);
       productRepository.deleteById(buildProdSku(sku));
    }

    private ProductDto convertToDto(Product Product) {
        ProductDto ProductDto = modelMapper.map(Product, ProductDto.class);
        return ProductDto;
    }
    
    private Product convertToEntity(ProductDto productDto) throws ParseException {
        Product product = modelMapper.map(productDto, Product.class);
        product.setSku(buildProdSku(productDto.getSku()));
        return product;
    }    
}