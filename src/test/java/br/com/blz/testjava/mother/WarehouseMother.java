package br.com.blz.testjava.mother;

import br.com.blz.testjava.domain.enums.LocalityEnum;
import br.com.blz.testjava.domain.enums.WarehouseTypeEnum;
import br.com.blz.testjava.domain.request.WarehouseRequest;
import br.com.blz.testjava.domain.response.WarehouseResponse;
import br.com.blz.testjava.model.Warehouse;

public class WarehouseMother {

    private WarehouseMother() {
    }

    public static Warehouse getWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setQuantity(12);
        warehouse.setType(WarehouseTypeEnum.ECOMMERCE);
        warehouse.setLocality(LocalityEnum.SP);
        return warehouse;
    }

    public static Warehouse getWarehouse(Integer quantity) {
        Warehouse warehouse = getWarehouse();
        warehouse.setQuantity(quantity);
        return warehouse;
    }

    public static WarehouseRequest getWarehouseRequest() {
        WarehouseRequest warehouseRequest = new WarehouseRequest();
        warehouseRequest.setLocality(LocalityEnum.MOEMA);
        warehouseRequest.setQuantity(10);
        warehouseRequest.setType(WarehouseTypeEnum.PHYSICAL_STORE);
        return warehouseRequest;
    }

    public static WarehouseRequest getWarehouseRequest(Integer quantity) {
        WarehouseRequest warehouseRequest = getWarehouseRequest();
        warehouseRequest.setQuantity(quantity);
        return warehouseRequest;
    }

    public static WarehouseResponse getWarehouseResponse() {
        WarehouseResponse warehouseResponse = new WarehouseResponse();
        warehouseResponse.setType(WarehouseTypeEnum.PHYSICAL_STORE);
        warehouseResponse.setLocality(LocalityEnum.MOEMA);
        warehouseResponse.setQuantity(84);
        return warehouseResponse;
    }
}
