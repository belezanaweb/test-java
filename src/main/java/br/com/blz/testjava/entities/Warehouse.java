package br.com.blz.testjava.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.blz.testjava.entities.Product.ProductBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "locality",
    "quantity",
    "type"
})
public class Warehouse {

    @JsonProperty("locality")
    private String locality;
    @JsonProperty("quantity")
    private Long quantity;
    @JsonProperty("type")
    private String type;
}
