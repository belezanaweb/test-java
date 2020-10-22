package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.ProductRequestDTO;
import br.com.blz.testjava.dto.ProductResponseDTO;
import br.com.blz.testjava.dto.ProductUpdateRequestDTO;
import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) throws ProductAlreadyExistsException {
        log.info("Start -  createProduct.....");
        log.info("Checking if product already exists.....");
        Optional<Product> optProduct = productRepository.findBySku(requestDTO.getSku());
        if (optProduct.isPresent()) {
           throw new ProductAlreadyExistsException(requestDTO.getSku());
        }
        log.info("No product found - creating new Product.....");
        Product product = requestDTO.toEntity();
        product = productRepository.save(product);
        log.info("End -  createProduct.....");
        return product.toResponseDTO();

    }

    public ProductResponseDTO findProductBySku(Long sku) throws ProductNotFoundException {
        log.info("Start -  findProductBySku.....");
        log.info("Looking for product with sku {}.....", sku);
        Optional<Product> product = productRepository.findBySku(sku);
        log.info("Product found for sku {}.....", sku);
        log.info("End -  findProductBySku.....");
        return product.orElseThrow(()-> new ProductNotFoundException(sku)).toResponseDTO();

    }

    public ProductResponseDTO deleteProduct(Long sku)  throws ProductNotFoundException{
        log.info("Start -  deleteProduct.....");
        log.info("Looking for product with sku {}.....", sku);
        Product product = productRepository.findBySku(sku).orElseThrow(()-> new ProductNotFoundException(sku));
        log.info("Product found for sku {}.....", sku);
        product = productRepository.delete(product.getSku());
        log.info("Product Deleted.....");
        log.info("End -  deleteProduct.....");
        return product.toResponseDTO();
    }

    public ProductResponseDTO updateProduct(Long sku, ProductUpdateRequestDTO productRequestDTO) throws ProductNotFoundException {
        log.info("Start -  updateProduct.....");
        log.info("Looking for product with sku {}.....", sku);
        Product product = productRepository.findBySku(sku).orElseThrow(()-> new ProductNotFoundException(sku));
        log.info("Product found for sku {}.....", sku);
        log.info("Updating product Information.....", sku);
        product = productRepository.save(productRequestDTO.toEntity(product.getSku()));
        log.info("End -  updateProduct.....");
        return product.toResponseDTO();
    }
}
