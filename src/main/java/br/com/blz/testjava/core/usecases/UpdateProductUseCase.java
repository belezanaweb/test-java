package br.com.blz.testjava.core.usecases;

import br.com.blz.testjava.core.domain.Product;
import br.com.blz.testjava.core.exceptions.ProductException;
import br.com.blz.testjava.core.ports.FindProductCachePort;
import br.com.blz.testjava.core.ports.UpdateProductCachePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateProductUseCase {

    private final UpdateProductCachePort updateProductCachePort;
    private final FindProductCachePort findProductCachePort;

    public void execute(final Product newProduct, final String sku) {
        Product p = findProductCachePort.findProduct(sku);
        if(p == null)
            throw new ProductException(String.valueOf(NOT_FOUND.value()), "Non-existent product");
        updateProductCachePort.updateProduct(newProduct, sku);
    }

}
