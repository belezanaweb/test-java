package br.com.blz.testjava.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "PRODUCT", schema="BLZ_PRODUCTS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "sku")
public final class Product implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sku")
	private Long sku;

	@Column(name = "name")
	private String name;
	
	@JsonProperty("isMarketable")
	private boolean marketable;	
 
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "product") 
	private Inventory inventory;
	
	
}
