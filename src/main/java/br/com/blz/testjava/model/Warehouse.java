package br.com.blz.testjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WAREHOUSES")
public final class Warehouse {

	@Id
	@GeneratedValue
	private Integer id;

	private String locality;

	private int quantity;

	@NotEmpty
	private String type;
}
