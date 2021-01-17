package br.com.blz.testjava.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class InventoryDTO {
    @NonNull
    private List<WareHouseDTO> warehouses;

}
