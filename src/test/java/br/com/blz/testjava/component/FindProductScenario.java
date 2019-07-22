package br.com.blz.testjava.component;

import br.com.blz.testjava.domain.api.request.CreateProductRequest;
import br.com.blz.testjava.domain.api.request.InventoryRequest;
import br.com.blz.testjava.domain.api.request.WarehouseRequest;
import io.restassured.RestAssured;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.blz.testjava.controller.BaseController.PRODUCT_RESOURCE;
import static br.com.blz.testjava.controller.BaseController.SKU_PATH_VARIABLE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class FindProductScenario extends BaseTest {

    private static final int SKU_VALUE = 43264;
    private static final String NAME_VALUE = "Nome qualquer";
    private static final String LOCALITY1_VALUE = "SP";
    private static final String LOCALITY2_VALUE = "MOEMA";
    private static final int QUANTITY1_VALUE = 12;
    private static final int QUANTITY2_VALUE = 3;
    private static final int QUANTITY_SUM_VALUE = QUANTITY1_VALUE + QUANTITY2_VALUE;
    private static final String TYPE1_VALUE = "ECOMMERCE";
    private static final String TYPE2_VALUE = "PHYSICAL_STORE";

    @Test
    public void findProduct_ok() {
        when(repository.findBySku(anyLong())).thenReturn(Optional.of(createRequest()));

        RestAssured.get(getPath())
            .then()
            .statusCode(200)
            .body(SKU_KEY, equalTo(SKU_VALUE))
            .body(NAME_KEY, equalTo(NAME_VALUE))
            .body(INVENTORY_QUANTITY_KEY, equalTo(QUANTITY_SUM_VALUE))
            .body(WAREHOUSES_LOCALITY_KEY, hasItems(LOCALITY1_VALUE, LOCALITY2_VALUE))
            .body(WAREHOUSES_QUANTITY_KEY, hasItems(QUANTITY1_VALUE, QUANTITY2_VALUE))
            .body(WAREHOUSES_TYPE_KEY, hasItems(TYPE1_VALUE, TYPE2_VALUE))
            .body(MARKETABLE_KEY, equalTo(true));
    }

    @Test
    public void findProduct_notFound() {
        when(repository.findBySku(anyLong())).thenReturn(Optional.empty());

        RestAssured.get(getPath())
            .then()
            .statusCode(404);
    }

    private CreateProductRequest createRequest() {
        CreateProductRequest request = new CreateProductRequest();
        InventoryRequest inventory = new InventoryRequest();
        List<WarehouseRequest> warehouses = new ArrayList<>();
        WarehouseRequest warehouse = new WarehouseRequest();

        warehouse.setLocality(LOCALITY1_VALUE);
        warehouse.setType(TYPE1_VALUE);
        warehouse.setQuantity((long) QUANTITY1_VALUE);

        WarehouseRequest warehouse2 = new WarehouseRequest();

        warehouse2.setLocality(LOCALITY2_VALUE);
        warehouse2.setType(TYPE2_VALUE);
        warehouse2.setQuantity((long) QUANTITY2_VALUE);

        warehouses.add(warehouse);
        warehouses.add(warehouse2);

        inventory.setWarehouses(warehouses);

        request.setSku((long) SKU_VALUE);
        request.setName(NAME_VALUE);
        request.setInventory(inventory);
        return request;
    }

    private String getPath() {
        return HOST + port + PRODUCT_RESOURCE.replace(SKU_PATH_VARIABLE, String.valueOf(SKU_VALUE));
    }
}
