package br.com.blz.testjava.domains;

import br.com.blz.testjava.domains.enums.TypeInventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Warehouse {

    private String locality;
    private Integer quantity;
    private TypeInventory type;

}
