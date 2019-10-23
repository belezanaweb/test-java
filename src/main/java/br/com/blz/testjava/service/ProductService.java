package br.com.blz.testjava.service;

import br.com.blz.testjava.controller.dto.ProductDTO;
import br.com.blz.testjava.converter.ProductConverter;
import br.com.blz.testjava.converter.ProductDtoConverter;
import br.com.blz.testjava.exception.ResourceAlreadyExistsException;
import br.com.blz.testjava.exception.ResourceNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductRepository repository;
    @Autowired private ProductDtoConverter productDtoconverter;
    @Autowired private ProductConverter productConverter;

    public ProductDTO findBy(final Long sku) throws ResourceNotFoundException {
        final Product product = this.findProduct(sku);

        return productDtoconverter.perform(product);
    }

    public ProductDTO save(final ProductDTO productDTO) throws ResourceAlreadyExistsException {
        final Optional<Product> optional = repository.findById(productDTO.getSku());

        if (optional.isPresent()) {
            throw new ResourceAlreadyExistsException();
        }

        final Product product = productConverter.perform(productDTO);
        repository.save(product);

        return productDtoconverter.perform(product);
    }

    public ProductDTO update(final Long sku, final ProductDTO productDTO) throws ResourceNotFoundException {
        final Product product = this.findProduct(sku);
        return this.updateExistingProduct(product, productDTO);
    }

    private ProductDTO updateExistingProduct(final Product product, final ProductDTO productDTO) {
        final Product productConverted = productConverter.perform(productDTO);
        product.setName(productConverted.getName());
        product.setInventory(productConverted.getInventory());

        return productDtoconverter.perform(product);
    }

    public void delete(final Long sku) throws ResourceNotFoundException {
        this.findProduct(sku);
        repository.deleteById(sku);
    }

    private Product findProduct(final Long sku) throws ResourceNotFoundException {
        final Optional<Product> optional = repository.findById(sku);
        return optional.orElseThrow(ResourceNotFoundException::new);
    }
}
