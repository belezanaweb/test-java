package br.com.blz.testjava.core.usecases;

import br.com.blz.testjava.core.domain.Product;
import br.com.blz.testjava.core.exceptions.ProductException;
import br.com.blz.testjava.core.ports.FindProductCachePort;
import br.com.blz.testjava.web.response.FindProductResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindProductUseCase {

    private final FindProductCachePort findProductCachePort;

    public FindProductResponseDTO execute(final String sku) {
        return getProduct(sku).toDto();
    }

    private Product getProduct(String sku){
        Product product = findProductCachePort.findProduct(sku);
        if(product == null)
            throw new ProductException(String.valueOf(NOT_FOUND.value()), "Non-existent product");
        else return product;
    }

}
