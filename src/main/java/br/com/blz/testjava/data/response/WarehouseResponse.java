package br.com.blz.testjava.data.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseResponse {

    private String locality;
    private Integer quantity;
    private PointOfServiceTypeResponse type;

}
