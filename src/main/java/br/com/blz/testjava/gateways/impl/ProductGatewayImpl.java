package br.com.blz.testjava.gateways.impl;

import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.exceptions.NotFoundProductException;
import br.com.blz.testjava.gateways.ProductGateway;
import br.com.blz.testjava.gateways.jdbc.ProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

    private static final String NOT_FOUND = "Not found element by sku: ";
    private final ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Boolean existsProductBySKU(Integer sku) {
        return repository.existsBySku(sku);
    }

    @Override
    public Product findById(Integer sku) {
        return Optional.ofNullable(repository.findOne(sku))
            .orElseThrow(() -> new NotFoundProductException(NOT_FOUND + sku));
    }

    @Override
    public void deleteById(Integer sku) {
        repository.delete(sku);
    }
}
