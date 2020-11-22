package br.com.blz.testjava.api.resources;

import br.com.blz.testjava.api.dtos.ProductDTO;
import br.com.blz.testjava.api.exceptions.ApiErrors;
import br.com.blz.testjava.exceptions.BusinessException;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.services.ProductService;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

    private ProductService productService;
    private ModelMapper modelMapper;

    public ProductResource(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product = productService.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    @GetMapping("{sku}")
    public ProductDTO getProductBySku(@PathVariable Long sku) {
        return productService.getBySku(sku)
            .map(product -> modelMapper.map(product, ProductDTO.class))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{sku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long sku) {
        Product product = productService.getBySku(sku)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        productService.delete(product);
    }

    @PutMapping("{sku}")
    public ProductDTO update(@PathVariable Long sku, ProductDTO productDTO) {
        return productService.getBySku(sku)
            .map(product -> {
                product.setName(productDTO.getName());
                product.setInventory(product.getInventory());
                product = productService.update(product);

                return modelMapper.map(product, ProductDTO.class);
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrors handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return new ApiErrors(bindingResult);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ApiErrors handleBusinessException(BusinessException ex) {
        return new ApiErrors(ex);
    }

}
