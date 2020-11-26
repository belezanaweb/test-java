package br.com.blz.testjava.model.request;

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
public class InventoryRequest implements Serializable {

    private static final long serialVersionUID = -5424989238652686547L;

    private static final long EMPTY_WAREHOUSE = 0L;

    @Valid
    private Set<WareHouseRequest> warehouses = new HashSet<>();

}
