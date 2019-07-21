package br.com.blz.testjava.domain.api.request;

import br.com.blz.testjava.domain.api.response.CreateProductResponse;
import br.com.blz.testjava.domain.api.response.InventoryResponse;
import br.com.blz.testjava.domain.api.response.FindProductResponse;

import javax.validation.constraints.NotNull;

public class CreateProductRequest extends ReplaceProductRequest {

    @NotNull
    private Long sku;

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public FindProductResponse toFindResponse() {
        FindProductResponse response = new FindProductResponse();

        response.setSku(sku);
        response.setName(getName());

        InventoryResponse inventory = new InventoryResponse();
        inventory.setWarehouses(getInventory().getWarehouses());
        inventory.setQuantity(retrieveInventoryQuantity());

        response.setInventory(inventory);

        response.setMarketable(retriveMarketable());

        return response;
    }

    public CreateProductResponse toCreateResponse() {
        return toCreateResponse(false);
    }

    public CreateProductResponse toCreateResponse(boolean updated) {
        CreateProductResponse response = new CreateProductResponse();
        response.setSku(sku);
        response.setInventoryQuantity(retrieveInventoryQuantity());
        response.setMarketable(retriveMarketable());
        response.setUpdated(updated);

        return response;
    }

    private Long retrieveInventoryQuantity() {
        return getInventory().getWarehouses().stream()
            .map(WarehouseRequest::getQuantity)
            .reduce(0L, Long::sum);
    }

    private Boolean retriveMarketable() {
        return retrieveInventoryQuantity() > 0;
    }
}
