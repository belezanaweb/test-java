package br.com.blz.testjava.gateways.http.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WarehouseDTO {

    @NotEmpty(message = "Locality can not be empty!")
    private String locality;

    @NotNull(message = "Quantity can not be null!")
    private Integer quantity;

    @NotEmpty(message = "Warehouse Type can not be empty!")
    private String type;
}
