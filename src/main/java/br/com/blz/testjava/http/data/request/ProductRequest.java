package br.com.blz.testjava.http.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @NotNull(message = "sku is required")
    @Min(value = 1, message = "the min sku is 1")
    private Long sku;
    @NotNull(message = "name is required")
    @Size(min = 3, message = "name must have least three characters")
    private String name;
    @NotNull(message = "the invetory object is required")
    private InventoryRequest inventory;
}
