package br.com.blz.testjava.gateways.http.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WarehouseResponseDTO {

    private String locality;
    private Integer quantity;
    private String warehouseType;
}
