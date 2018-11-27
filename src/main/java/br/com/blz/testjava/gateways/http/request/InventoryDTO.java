package br.com.blz.testjava.gateways.http.request;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InventoryDTO {

    private List<WarehouseDTO> warehouses;
}
