package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.ProductRequestDTO;
import br.com.blz.testjava.dto.ProductResponseDTO;
import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) throws ProductAlreadyExistsException {

        Optional<Product> optProduct = productRepository.findBySku(requestDTO.getSku());
        if (optProduct.isPresent()) {
           throw new ProductAlreadyExistsException(requestDTO.getSku());
        }
        Product product = requestDTO.toEntity();
        product = productRepository.save(product);
        return product.toResponseDTO();

    }

    public ProductResponseDTO findProductBySku(Long sku) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findBySku(sku);
        return product.orElseThrow(()-> new ProductNotFoundException(sku)).toResponseDTO();

    }
}
