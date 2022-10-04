package br.com.blz.testjava.application.product.update;

import br.com.blz.testjava.application.UseCaseTest;
import br.com.blz.testjava.application.product.InventoryInput;
import br.com.blz.testjava.domain.product.Product;
import br.com.blz.testjava.domain.product.ProductGateway;
import br.com.blz.testjava.domain.product.Sku;
import br.com.blz.testjava.domain.product.Warehouse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class UpdateProductUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultUpdateProductUseCase useCase;

    @Mock
    private ProductGateway productGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(productGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsUpdateProduct_shouldUpdatedProduct() {
        final var sku = 43264L;
        final var expectedQuantity = 10L;
        final var expectedIsMarketable = true;
        final var expectedName = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
        final var warehouses = asList(Warehouse.newWarehouse("SP", 8, "ECOMMERCE"),
            Warehouse.newWarehouse("MOEMA", 2, "PHYSICAL_STORE"));
        final var productCommand = UpdateProductCommand.with(sku, expectedName, InventoryInput.with(warehouses));
        final var expectedSku = Sku.from(sku);
        final var product = Product.newProduct(sku, expectedName, warehouses);

        when(productGateway.findBySku(eq(expectedSku))).thenReturn(Optional.of(product.clone()));
        when(productGateway.update(any())).thenReturn(product);

        final var actualProduct = useCase.execute(productCommand);

        Assertions.assertEquals(expectedSku.getValue(), actualProduct.sku());
        Assertions.assertEquals(expectedName, actualProduct.name());
        Assertions.assertEquals(expectedQuantity, actualProduct.inventory().quantity());
        Assertions.assertEquals(expectedIsMarketable, actualProduct.isMarketable());
    }
}
