package br.com.blz.testjava.domains;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Inventory {

    private Integer quantity;
    private List<Warehouse> warehouses;

}
