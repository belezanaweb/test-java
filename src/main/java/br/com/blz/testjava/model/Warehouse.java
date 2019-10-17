package br.com.blz.testjava.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WAREHOUSE", schema = "BLZ_PRODUCTS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "warehouse_id")
public final class Warehouse {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "warehouse_id")
	private Integer warehouse_id;

	@NotEmpty
	@Column(name = "locality")
	private String locality;

	@Column(name = "quantity")
	private int quantity;

	@NotEmpty
	@Column(name = "type")
	private String type;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", nullable = false) 
    private Inventory inventory;
 
	
}