package br.com.blz.testjava.entities;

import br.com.blz.testjava.enums.WareHouseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WareHouse implements Serializable {

    private static final long serialVersionUID = 866521961839054792L;

    @NotBlank
    private String locality;

    @NotNull
    private Integer quantity;

    @NotNull
    private WareHouseTypeEnum type;

}
