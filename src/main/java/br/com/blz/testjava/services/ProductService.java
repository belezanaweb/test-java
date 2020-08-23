package br.com.blz.testjava.services;

import br.com.blz.testjava.dtos.ProductDTO;
import br.com.blz.testjava.exceptions.SkuAlreadyExistsException;
import br.com.blz.testjava.exceptions.SkuNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    private final Map<Long, ProductDTO> products = new HashMap<>();

    public ProductDTO createProduct(ProductDTO productDTO) {
        if (products.containsKey(productDTO.getSku())) {
            throw new SkuAlreadyExistsException(productDTO.getSku());
        }
        products.put(productDTO.getSku(), productDTO);
        log.info("Product created: {}", productDTO);
        return productDTO;
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        ProductDTO existingProduct = findProduct(id).orElseThrow(() -> new SkuNotFoundException(id));
        log.info("Updating product: {}", existingProduct);
        log.info("New values: {}", productDTO);
        products.put(productDTO.getSku(), productDTO);
        return productDTO;
    }

    public ProductDTO getProduct(long id) {
        ProductDTO productDTO = findProduct(id).orElseThrow(() -> new SkuNotFoundException(id));
        log.info("Product found: {}", productDTO);
        return productDTO;
    }

    public void deleteProduct(long id) {
        ProductDTO productDTO = findProduct(id).orElseThrow(() -> new SkuNotFoundException(id));
        products.remove(id, productDTO);
        log.info("Product deleted: {}", productDTO);
    }

    private Optional<ProductDTO> findProduct(long id) {
        return Optional.ofNullable(products.get(id));
    }
}
