package br.com.blz.testjava.domain.product;

import br.com.blz.testjava.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ProductTest {

    @Test
    public void givenAValidParams_whenCallNewProduct_thenInstantiateAProduct() {
        final var expectedSku = 43264L;
        final var expectedName = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
        final var expectedWarehouses = Arrays.asList(Warehouse.newWarehouse("SP", 12, "ECOMMERCE"),
            Warehouse.newWarehouse("MOEMA", 3, "PHYSICAL_STORE"));
        final var actualProduct = Product.newProduct(expectedSku, expectedName, expectedWarehouses);
        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(expectedSku, actualProduct.getId().getValue());
        Assertions.assertEquals(expectedWarehouses.size(), actualProduct.getWarehouses().size());
    }

    @Test
    public void givenAInvalidParam_whenCallNewProduct_shouldReturnProductError() {
        Assertions.assertThrows(DomainException.class, () -> Product.newProduct(null, null, null), "'SKU' não pode ser nulo.");
    }
}
