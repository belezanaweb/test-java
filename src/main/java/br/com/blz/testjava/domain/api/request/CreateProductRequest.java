package br.com.blz.testjava.domain.api.request;

import br.com.blz.testjava.domain.api.response.InventoryResponse;
import br.com.blz.testjava.domain.api.response.ProductResponse;

public class CreateProductRequest extends ReplaceProductRequest {

    private Long sku;

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public ProductResponse toResponse() {
        ProductResponse productResponse = new ProductResponse();

        productResponse.setSku(sku);
        productResponse.setName(getName());

        InventoryResponse inventory = new InventoryResponse();
        inventory.setWarehouses(getWarehouses());
        inventory.setQuantity(retrieveInventoryQuantity());

        productResponse.setInventory(inventory);

        productResponse.setMarketable(inventory.getQuantity() > 0);


        return productResponse;
    }

    public Long retrieveInventoryQuantity() {
        return getWarehouses().stream().map(WarehouseRequest::getQuantity).reduce(0L, Long::sum);
    }
}
