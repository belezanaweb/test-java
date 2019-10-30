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
public class Product {

    @Null(groups = OnUpdate.class)
    @NotNull(groups = Default.class)
    private Integer sku;

    @NotBlank(groups = {Default.class, OnUpdate.class})
    private String name;

    @NotNull(groups = {Default.class, OnUpdate.class})
    @Valid
    private Inventary inventary;

    public boolean isMarketable() {
        return this.getInventary().getQuantity() > 0;
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

    public Inventary getInventary() {
        return inventary;
    }

    public void setInventary(Inventary inventary) {
        this.inventary = inventary;
    }
}
