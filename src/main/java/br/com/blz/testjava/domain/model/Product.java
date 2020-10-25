package br.com.blz.testjava.domain.model;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 6923768843577244901L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sku;
    
	private String name;
    
	@ManyToOne
	@JoinColumn(nullable = false)
    private Inventory inventory;
    
	public Boolean isMarketable() {
		return Optional.ofNullable(this.inventory).orElse(new Inventory()).getQuantity() > 0;
	}
}
