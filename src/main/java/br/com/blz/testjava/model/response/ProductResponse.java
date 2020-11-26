package br.com.blz.testjava.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static java.util.Objects.nonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse implements Serializable {

    private static final long serialVersionUID = 7922147391911260422L;

    private String sku;

    private String name;

    private InventoryResponse inventory;

    public boolean marketable;

}
