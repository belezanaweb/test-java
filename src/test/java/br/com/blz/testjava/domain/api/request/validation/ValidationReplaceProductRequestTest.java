package br.com.blz.testjava.domain.api.request.validation;

import br.com.blz.testjava.domain.api.request.ReplaceProductRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidationReplaceProductRequestTest extends BaseValidationTest {

    @Test
    public void success() {
        ReplaceProductRequest request = createReplaceProductRequest();

        assertTrue(validator.validate(request).isEmpty());
    }

    @Test
    public void validateInvalidName() {
        ReplaceProductRequest request = createReplaceProductRequest();

        request.setName(null);
        assertEquals(1, validator.validate(request).size());

        request.setName("");
        assertEquals(1, validator.validate(request).size());

        request.setName("  ");
        assertEquals(1, validator.validate(request).size());
    }

    @Test
    public void validateNullInventory() {
        ReplaceProductRequest request = createReplaceProductRequest();

        request.setInventory(null);
        assertEquals(1, validator.validate(request).size());
    }

    private ReplaceProductRequest createReplaceProductRequest() {
        ReplaceProductRequest request = new ReplaceProductRequest();

        request.setName("nome");
        request.setInventory(ValidationInventoryRequestTest.createInventoryRequest());

        return request;
    }
}
