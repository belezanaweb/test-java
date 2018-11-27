package br.com.blz.testjava.gateways.http.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InventoryDTO {

    private List<WarehouseDTO> warehouses;
}
