package br.com.blz.testjava.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "sku")
public final class Product implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sku")
	private Long sku;

	@Column(name = "name")
	private String name;
	
	@Column(name = "marketable")
	private boolean marketable;	
 
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL) 
	private Inventory inventory;
	
	
}
