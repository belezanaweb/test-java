package br.com.blz.testjava.dto;

import br.com.blz.testjava.dto.support.PersistableDto;
import br.com.blz.testjava.dto.support.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@JsonPropertyOrder("sku")
@Data
public class ProductDto implements PersistableDto<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU product")
    @NotNull(groups = ValidationGroups.Create.class, message = "sku {javax.validation.constraints.NotNull.message}")
    @JsonProperty("sku")
    private Long id;

    @NotEmpty
    private String name;

    private InventoryDto inventory;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean marketable;

}
