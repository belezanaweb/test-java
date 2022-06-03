package br.com.blz.testjava.core.usecases;

import br.com.blz.testjava.core.domain.Product;
import br.com.blz.testjava.core.exceptions.ProductException;
import br.com.blz.testjava.core.ports.FindProductCachePort;
import br.com.blz.testjava.core.ports.SaveProductCachePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateProductUseCase {

    private final SaveProductCachePort saveProductCachePort;
    private final FindProductCachePort findProductCachePort;

    public void execute(final Product product) {
        validateIfExists(product);
        saveProductCachePort.saveProduct(product);
    }

    private void validateIfExists(Product product){
        if(findProductCachePort.findProduct(product.getSku().toString()) != null)
            throw new ProductException(String.valueOf(BAD_REQUEST.value()), "There is already a product registered with this sku.");
    }

}
