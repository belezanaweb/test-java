package br.com.blz.testjava.domain.api.response;

import br.com.blz.testjava.domain.api.request.InventoryRequest;

public class InventoryResponse extends InventoryRequest {

    private Long quantity;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
