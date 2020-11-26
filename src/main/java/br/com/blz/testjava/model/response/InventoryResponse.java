package br.com.blz.testjava.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.beans.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse implements Serializable {

    private static final long serialVersionUID = 7896554457969104356L;

    private static final long EMPTY_WAREHOUSE = 0L;

    private Set<WareHouseResponse> warehouses = new HashSet<>();

    public Long quantity;

}
