package br.com.blz.testjava.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Inventory implements Serializable {

	private static final long serialVersionUID = 7309216694494407138L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @OneToMany
    private List<Warehouse> warehouses;
    
    public Integer getQuantity() {		
    	return Optional.ofNullable(warehouses).orElse(new ArrayList<>())
    			.stream().mapToInt(Warehouse::getQuantity).sum();
    }

}
