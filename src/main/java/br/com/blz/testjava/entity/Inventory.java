package br.com.blz.testjava.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory implements Serializable {

    /**
	 * 
	 */
	
	
	@Id @GeneratedValue
	private Long id;
    private Long sku;
    private String name;
    @Transient
    private List<Warehouse> warehouses;
    private boolean marktable;

    public Integer getQuantity() {
        return this.warehouses.stream()
                .map(p -> p.getQuantity())
                .reduce(0, Integer::sum);
    }

    public boolean isMarktable() {
        Integer sum = this.warehouses.stream()
                .map(p -> p.getQuantity())
                .reduce(0, Integer::sum);
        if (sum > 0) return true;
        return marktable;
    }

	public void setSku(Long sku) {
		this.sku = sku;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	public void setMarktable(boolean marktable) {
		this.marktable = marktable;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    
    
    
}
