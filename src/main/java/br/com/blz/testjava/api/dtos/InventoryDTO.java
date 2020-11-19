package br.com.blz.testjava.api.dtos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDTO {

    private List<WarehouseDTO> warehouses;

}
