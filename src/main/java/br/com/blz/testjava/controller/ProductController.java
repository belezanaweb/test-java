package br.com.blz.testjava.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.ProductExistException;
import br.com.blz.testjava.mapper.impl.ProductMapper;
import br.com.blz.testjava.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    private ProductMapper productMapper = new ProductMapper();

    @GetMapping("/{sku}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("sku") String sku) {
        Optional<Product> product = productRepository.findBySku(sku);

        if (product.isPresent()) {
            return new ResponseEntity<>(productMapper.fromEntityToDto(product.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    /* Este endpoint foi desenvolvido apenas para facilitar na visualizações dos dados */
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = new ArrayList<Product>();

        productRepository.findAll().forEach(products::add);

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createCpf(@RequestBody Product product) {
        if (productRepository.findBySku(product.getSku()).isPresent()) {
          throw new ProductExistException();
        }

        Product _product = productRepository.save(product);
        return new ResponseEntity<>(productMapper.fromEntityToDto(_product), HttpStatus.CREATED);
    }

    @PutMapping("/{sku}")
    public ResponseEntity<ProductDto> updateProduct(    @RequestBody Product product,
                                                        @PathVariable("sku") String sku) {

        Optional<Product> _product = productRepository.findBySku(sku);
        if (_product.isEmpty()) {
            return new ResponseEntity<ProductDto>(HttpStatus.NOT_FOUND);
        }

        _product.get().setSku(product.getSku());
        _product.get().setName(product.getName());
        _product.get().setInventory(product.getInventory());

        Product _productUpdated = productRepository.save(_product.get());

        return new ResponseEntity<>(productMapper.fromEntityToDto(_productUpdated), HttpStatus.CREATED);

    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("sku") String sku) {

        Optional<Product> _product = productRepository.findBySku(sku);
        if (_product.isPresent()) {
            productRepository.delete(_product.get());
            return new ResponseEntity<Product>(_product.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);

    }

}
