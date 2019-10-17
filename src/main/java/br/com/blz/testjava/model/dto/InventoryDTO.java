package br.com.blz.testjava.model.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class InventoryDTO {

    private Integer inventory_id;
    
    private int quantity;
    
    private ProductDTO product;
    
    @NotEmpty
    @Valid
    private List<WarehouseDTO> warehouses;
 
    
    
}
