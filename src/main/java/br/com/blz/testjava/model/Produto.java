package br.com.blz.testjava.model;

import br.com.blz.testjava.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Produto {

	@Null(groups = OnUpdate.class)
    @NotNull(groups = Default.class)
    private Integer sku;

    @NotBlank(groups = {Default.class, OnUpdate.class})
    private String name;

    @NotNull(groups = {Default.class, OnUpdate.class})
    @Valid
    private Inventory inventory;

    public boolean isMarketable() {
        return this.getInventory().getQuantity() > 0;
    }

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
 

}