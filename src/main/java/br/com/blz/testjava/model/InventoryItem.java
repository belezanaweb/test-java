package br.com.blz.testjava.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.blz.testjava.model.enums.StockType;
import lombok.Data;

@Data
@Entity(name = "inventory_item")
@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"locality", "type", "sku_product"})
	)
public class InventoryItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String locality;
	
	@Column
	private Long quantity;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StockType type;
	
	@PrePersist @PreUpdate 
	private void prepare(){
        this.locality = locality.toUpperCase();
    }
}
