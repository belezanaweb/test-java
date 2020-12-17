package br.com.blz.testjava.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    @NotNull 
    private String name;
    
    private boolean isMarketable;
    
    @NotNull 
    private Long sku;
    
    @NotNull 
    private InventoryDTO inventory;
    
}
