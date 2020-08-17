package br.com.blz.testjava.domain.request;

import javax.validation.constraints.NotNull;

public class ProductCreateRequest extends ProductUpdateRequest {

    @NotNull
    private Long sku;

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }
}
