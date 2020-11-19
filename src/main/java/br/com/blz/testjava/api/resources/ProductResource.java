package br.com.blz.testjava.api.resources;

import br.com.blz.testjava.api.dtos.ProductDTO;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product = productService.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

}
