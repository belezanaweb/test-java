package br.com.blz.testjava.application.product.create;

import br.com.blz.testjava.application.UseCaseTest;
import br.com.blz.testjava.application.product.InventoryInput;
import br.com.blz.testjava.domain.product.ProductGateway;
import br.com.blz.testjava.domain.product.Warehouse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CreateProductUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateProductUseCase useCase;

    @Mock
    private ProductGateway productGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(productGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateProduct_shouldReturnSku() {
        final var expectedSku = 43264L;
        final var expectedName = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
        final var expectedInventory = InventoryInput.with(asList(Warehouse.newWarehouse("SP", 12, "ECOMMERCE"),
            Warehouse.newWarehouse("MOEMA", 3, "PHYSICAL_STORE")));

        final var command = CreateProductCommand.with(expectedSku, expectedName, expectedInventory);

        when(productGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(command);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.sku());

        Mockito.verify(productGateway, times(1)).create(argThat(product -> Objects.equals(expectedSku, product.getId().getValue())));
    }

}
