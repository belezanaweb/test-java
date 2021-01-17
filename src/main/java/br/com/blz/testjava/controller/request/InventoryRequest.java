package br.com.blz.testjava.controller.request;

import br.com.blz.testjava.domain.objectvalue.Warehouse;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class InventoryRequest {

    @NotNull
    private List<Warehouse> warehouses;
}
