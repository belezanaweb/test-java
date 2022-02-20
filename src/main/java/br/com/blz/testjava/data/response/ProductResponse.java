package br.com.blz.testjava.data.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProductResponse {


    private String sku;
    private String name;
    private InventoryResponse inventory;
    private boolean isMarketable;
}
