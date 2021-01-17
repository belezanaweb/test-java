package br.com.blz.testjava.dto;

import br.com.blz.testjava.model.enums.WarehouseTypes;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

@Data
@ToString
@NoArgsConstructor
public class WareHouseDTO {
    @NonNull
    private String locality;
    @NonNull
    private Integer quantity;
    @NonNull
    private WarehouseTypes type;
}
