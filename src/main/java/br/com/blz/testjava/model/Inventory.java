package br.com.blz.testjava.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.annotation.Transient;
import org.springframework.util.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
	
	private List<@Valid Warehouse> warehouses;
	
	@Transient 
	public Long getQuantity() {
		return CollectionUtils.isEmpty(this.warehouses) ? 0L : this.warehouses.stream().collect(Collectors.summingLong(Warehouse::getQuantity));
	}
	
}
