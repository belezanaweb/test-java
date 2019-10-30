package br.com.blz.testjava.model;

import br.com.blz.testjava.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventary {

    @NotNull(groups = {Default.class, OnUpdate.class})
    @Valid
	private List<Warehouse> warehouses;

	public Integer getQuantity() {
		return this.getWarehouses()
            .stream()
            .map(Warehouse::getQuantity)
            .filter(Objects::nonNull)
            .reduce(Integer::sum)
            .orElse(0);
	}

	public List<Warehouse> getWarehouses() {
		return this.warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
}
