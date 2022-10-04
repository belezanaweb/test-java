package br.com.blz.testjava.application.product.delete;

import br.com.blz.testjava.application.UseCaseTest;
import br.com.blz.testjava.application.product.InventoryInput;
import br.com.blz.testjava.application.product.create.CreateProductCommand;
import br.com.blz.testjava.domain.product.ProductGateway;
import br.com.blz.testjava.domain.product.Sku;
import br.com.blz.testjava.domain.product.Warehouse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

public class DeleteProductUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteProductUseCase useCase;

    @Mock
    private ProductGateway productGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(productGateway);
    }

    @Test
    public void givenAValidId_whenCallsDeleteProduct_shouldBeOK() {
        final var newProduct = CreateProductCommand.with(43264L,
            "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
            InventoryInput.with(asList(Warehouse.newWarehouse("SP", 12, "ECOMMERCE"),
                Warehouse.newWarehouse("MOEMA", 3, "PHYSICAL_STORE"))));
        final var expectedSku = Sku.from(newProduct.sku());

        doNothing().when(productGateway).deleteBySku(eq(expectedSku));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedSku.getValue()));

        Mockito.verify(productGateway, times(1)).deleteBySku(eq(expectedSku));
    }
}
