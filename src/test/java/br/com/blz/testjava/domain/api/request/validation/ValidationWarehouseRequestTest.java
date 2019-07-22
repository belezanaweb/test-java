package br.com.blz.testjava.domain.api.request.validation;

import br.com.blz.testjava.domain.api.request.WarehouseRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidationWarehouseRequestTest extends BaseValidationTest {

    @Test
    public void success() {
        WarehouseRequest request = createWarehouseRequest();

        assertTrue(validator.validate(request).isEmpty());
    }

    @Test
    public void validateInvalidLocality() {
        WarehouseRequest request = createWarehouseRequest();

        request.setLocality(null);
        assertEquals(1, validator.validate(request).size());

        request.setLocality("");
        assertEquals(1, validator.validate(request).size());

        request.setLocality("   ");
        assertEquals(1, validator.validate(request).size());
    }

    @Test
    public void validateInvalidQuantity() {
        WarehouseRequest request = createWarehouseRequest();

        request.setQuantity(null);
        assertEquals(1, validator.validate(request).size());

        request.setQuantity(-1L);
        assertEquals(1, validator.validate(request).size());
    }

    @Test
    public void validateInvalidType() {
        WarehouseRequest request = createWarehouseRequest();

        request.setType(null);
        assertEquals(1, validator.validate(request).size());

        request.setType("");
        assertEquals(1, validator.validate(request).size());

        request.setType("  ");
        assertEquals(1, validator.validate(request).size());

        request.setType("aaa");
        assertEquals(1, validator.validate(request).size());
    }

    static WarehouseRequest createWarehouseRequest() {
        WarehouseRequest request = new WarehouseRequest();

        request.setQuantity(0L);
        request.setType("ECOMMERCE");
        request.setLocality("SP");
        return request;
    }
}
