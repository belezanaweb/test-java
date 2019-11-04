package br.com.blz.testjava.http.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {
    @NotNull(message = "the warehouses are required")
    @Size(min = 1, message = "must have least one warehouse")
    private List<WarehouseRequest> warehouses;
}
