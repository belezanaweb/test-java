package br.com.blz.testjava.gateways.http.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InventoryResponseDTO {

    private Integer quantity;
    private List<WarehouseResponseDTO> warehouses;
}
