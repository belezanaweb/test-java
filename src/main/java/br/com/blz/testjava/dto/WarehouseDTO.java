package br.com.blz.testjava.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.blz.testjava.enums.WarehouseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WarehouseDTO {
    
    private Integer quantity;
    private String locality;
    private WarehouseType type;
    
}
