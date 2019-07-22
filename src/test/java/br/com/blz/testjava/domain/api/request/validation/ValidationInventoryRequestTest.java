package br.com.blz.testjava.domain.api.request.validation;

import br.com.blz.testjava.domain.api.request.InventoryRequest;
import br.com.blz.testjava.domain.api.request.WarehouseRequest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidationInventoryRequestTest extends BaseValidationTest {

    @Test
    public void success() {
        InventoryRequest request = createInventoryRequest();

        assertTrue(validator.validate(request).isEmpty());
    }

    @Test
    public void validateInvalidWarehouses() {
        InventoryRequest request = createInventoryRequest();

        request.setWarehouses(null);
        assertEquals(1, validator.validate(request).size());

        request.setWarehouses(new ArrayList<>());
        assertEquals(1, validator.validate(request).size());
    }

    static InventoryRequest createInventoryRequest() {
        InventoryRequest request = new InventoryRequest();

        List<WarehouseRequest> warehouses = new ArrayList<>();
        warehouses.add(ValidationWarehouseRequestTest.createWarehouseRequest());

        request.setWarehouses(warehouses);

        return request;
    }
}
