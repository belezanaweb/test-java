package br.com.blz.testjava.dto;

import br.com.blz.testjava.model.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class InventoryDTO {
    private List<Warehouse> warehouses;
}
