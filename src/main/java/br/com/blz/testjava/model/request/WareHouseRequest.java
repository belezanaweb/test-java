package br.com.blz.testjava.model.request;

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
public class WareHouseRequest implements Serializable {

    private static final long serialVersionUID = -8100552890250029829L;

    @NotBlank
    private String locality;

    @NotNull
    private Integer quantity;

    @NotNull
    private WareHouseTypeEnum type;

}
