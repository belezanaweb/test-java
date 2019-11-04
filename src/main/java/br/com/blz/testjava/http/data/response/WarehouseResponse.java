package br.com.blz.testjava.http.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseResponse {
    private String locality; //Deveria ser um Enum?
    private Integer quantity;
    private WarehouseTypeResponse type;
}
