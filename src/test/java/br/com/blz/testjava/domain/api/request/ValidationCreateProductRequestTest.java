package br.com.blz.testjava.domain.api.request;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidationCreateProductRequestTest extends BaseValidationTest {

    @Test
    public void success() {
        CreateProductRequest request = createProductRequest();

        assertTrue(validator.validate(request).isEmpty());
    }

    @Test
    public void validateNullSku() {
        CreateProductRequest request = createProductRequest();

        request.setSku(null);
        assertEquals(1, validator.validate(request).size());
    }

    @Test
    public void validateInvalidName() {
        CreateProductRequest request = createProductRequest();

        request.setName(null);
        assertEquals(1, validator.validate(request).size());

        request.setName("");
        assertEquals(1, validator.validate(request).size());

        request.setName("  ");
        assertEquals(1, validator.validate(request).size());
    }

    @Test
    public void validateNullInventory() {
        CreateProductRequest request = createProductRequest();

        request.setInventory(null);
        assertEquals(1, validator.validate(request).size());
    }

    private CreateProductRequest createProductRequest() {
        CreateProductRequest request = new CreateProductRequest();

        request.setSku(123L);
        request.setName("nome");
        request.setInventory(ValidationInventoryRequestTest.createInventoryRequest());

        return request;
    }
}
