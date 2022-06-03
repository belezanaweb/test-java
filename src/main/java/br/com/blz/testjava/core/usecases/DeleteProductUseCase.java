package br.com.blz.testjava.core.usecases;

import br.com.blz.testjava.core.ports.DeleteProductCachePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteProductUseCase {

    private final DeleteProductCachePort deleteProductCachePort;

    public void execute(final String sku) {
        deleteProductCachePort.deleteProduct(sku);
    }

}
