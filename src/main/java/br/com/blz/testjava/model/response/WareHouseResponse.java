package br.com.blz.testjava.model.response;

import br.com.blz.testjava.enums.WareHouseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WareHouseResponse implements Serializable {

    private static final long serialVersionUID = -394018903569412338L;

    private String locality;
    private Integer quantity;
    private String type;

}
