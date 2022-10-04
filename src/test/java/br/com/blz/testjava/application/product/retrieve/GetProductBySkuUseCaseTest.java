package br.com.blz.testjava.application.product.retrieve;

import br.com.blz.testjava.application.UseCaseTest;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class GetProductBySkuUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetProductBySkuUseCase useCase;

    @Mock
    private ProductGateway productGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(productGateway);
    }

    @Test
    public void givenAValidId_whenCallsGetProduct_shouldReturnProduct() {
        final var sku = 43264L;
        final var expectedQuantity = 15L;
        final var expectedIsMarketable = true;
        final var expectedName = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
        final var warehouses = asList(Warehouse.newWarehouse("SP", 12, "ECOMMERCE"),
            Warehouse.newWarehouse("MOEMA", 3, "PHYSICAL_STORE"));

        Product product = Product.newProduct(sku, expectedName, warehouses);
        final var expectedSku = Sku.from(sku);

        when(productGateway.findBySku(eq(expectedSku))).thenReturn(Optional.of(product.clone()));

        final var actualProduct = useCase.execute(expectedSku.getValue());

        Assertions.assertEquals(expectedSku.getValue(), actualProduct.sku());
        Assertions.assertEquals(expectedName, actualProduct.name());
        Assertions.assertEquals(expectedQuantity, actualProduct.inventory().quantity());
        Assertions.assertEquals(expectedIsMarketable, actualProduct.isMarketable());
    }

    @Test
    public void givenAValidId_whenCallsGetProduct_shouldReturnProductThenIsMarketableEqualFalse() {
        final var sku = 43264L;
        final var expectedQuantity = 0L;
        final var expectedIsMarketable = false;
        final var expectedName = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
        final var warehouses = asList(Warehouse.newWarehouse("SP", 0, "ECOMMERCE"),
            Warehouse.newWarehouse("MOEMA", 0, "PHYSICAL_STORE"));
        Product product = Product.newProduct(sku, expectedName, warehouses);
        final var expectedSku = Sku.from(sku);

        when(productGateway.findBySku(eq(expectedSku))).thenReturn(Optional.of(product.clone()));

        final var actualProduct = useCase.execute(expectedSku.getValue());

        Assertions.assertEquals(expectedSku.getValue(), actualProduct.sku());
        Assertions.assertEquals(expectedName, actualProduct.name());
        Assertions.assertEquals(expectedQuantity, actualProduct.inventory().quantity());
        Assertions.assertEquals(expectedIsMarketable, actualProduct.isMarketable());
    }
}
