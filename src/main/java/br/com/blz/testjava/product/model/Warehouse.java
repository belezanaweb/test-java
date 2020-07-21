package br.com.blz.testjava.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Warehouse {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(name = "id_inventory")
	@JsonIgnore
	private Long idInventory;
	
	@Column
	@NotNull
	private String locality;
	
	@NotNull
	private Long quantity;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private WarehouseType type;
}
