package br.com.blz.testjava.domain.api.request;

import br.com.blz.testjava.domain.api.response.CreateProductResponse;
import br.com.blz.testjava.domain.api.response.FindProductResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CreateProductRequestTest {

    @Test
    public void marketableRequestToFindResponse() {
        CreateProductRequest request = createRequest(true);
        FindProductResponse response = request.toFindResponse();

        toFindResponseAsserts(request, response, true);
    }

    @Test
    public void notMarketableRequestToFindResponse() {
        CreateProductRequest request = createRequest(false);
        FindProductResponse response = request.toFindResponse();

        toFindResponseAsserts(request, response, false);
    }

    @Test
    public void marketableRequestToCreateResponse() {
        CreateProductRequest request = createRequest(true);
        CreateProductResponse response = request.toCreateResponse(true);

        assertEquals(request.getSku(), response.getSku());
        assertEquals(getQuantitySum(request), response.getInventoryQuantity());
        assertTrue(response.getMarketable());
        assertTrue(response.getUpdated());

        response = request.toCreateResponse(false);

        assertEquals(request.getSku(), response.getSku());
        assertEquals(getQuantitySum(request), response.getInventoryQuantity());
        assertTrue(response.getMarketable());
        assertFalse(response.getUpdated());
    }

    @Test
    public void notMarketableRequestToCreateResponse() {
        CreateProductRequest request = createRequest(false);
        CreateProductResponse response = request.toCreateResponse(true);

        assertEquals(request.getSku(), response.getSku());
        assertEquals(getQuantitySum(request), response.getInventoryQuantity());
        assertFalse(response.getMarketable());
        assertTrue(response.getUpdated());

        response = request.toCreateResponse(false);

        assertEquals(request.getSku(), response.getSku());
        assertEquals(getQuantitySum(request), response.getInventoryQuantity());
        assertFalse(response.getMarketable());
        assertFalse(response.getUpdated());
    }

    private void toFindResponseAsserts(CreateProductRequest request, FindProductResponse response, boolean marketable) {
        assertEquals(request.getSku(), response.getSku());
        assertEquals(request.getName(), response.getName());
        assertNotNull(response.getInventory());
        assertEquals(marketable, response.getMarketable());
        assertEquals(getQuantitySum(request), response.getInventory().getQuantity());

        List<WarehouseRequest> requestWarehouses = request.getInventory().getWarehouses();
        List<WarehouseRequest> responseWarehouses = response.getInventory().getWarehouses();

        assertEquals(requestWarehouses.size(), responseWarehouses.size());

        for (int i = 0; i < requestWarehouses.size(); i++) {
            assertEquals(requestWarehouses.get(i).getQuantity(), responseWarehouses.get(i).getQuantity());
            assertEquals(requestWarehouses.get(i).getLocality(), responseWarehouses.get(i).getLocality());
            assertEquals(requestWarehouses.get(i).getType(), responseWarehouses.get(i).getType());
        }
    }

    private Long getQuantitySum(CreateProductRequest request) {
        return request.getInventory().getWarehouses()
            .stream().map(WarehouseRequest::getQuantity).reduce(0L, Long::sum);
    }

    private CreateProductRequest createRequest(boolean marketable) {
        CreateProductRequest request = new CreateProductRequest();
        InventoryRequest inventory = new InventoryRequest();
        List<WarehouseRequest> warehouses = new ArrayList<>();
        WarehouseRequest warehouse = new WarehouseRequest();

        warehouse.setLocality("SP");
        warehouse.setType("ECOMMERCE");
        warehouse.setQuantity(marketable ? 5L : 0L);

        warehouses.add(warehouse);
        warehouses.add(warehouse);

        inventory.setWarehouses(warehouses);

        request.setSku(123L);
        request.setName("Nome qualquer");
        request.setInventory(inventory);
        return request;
    }
}
