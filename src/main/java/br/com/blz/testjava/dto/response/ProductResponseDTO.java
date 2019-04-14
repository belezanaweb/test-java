package br.com.blz.testjava.dto.response;

import br.com.blz.testjava.dto.request.InventoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProductResponseDTO {

    private Integer sku;
    private String name;
    private Boolean marketable;
    private InventoryDTO inventory;
}
