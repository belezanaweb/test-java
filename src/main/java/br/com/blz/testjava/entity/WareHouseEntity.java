package br.com.blz.testjava.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Augusto Lemes
 * @since 16/06/2019
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WareHouseEntity implements Serializable{

	private static final long serialVersionUID = -8393760947978076976L;
	
	@Id
	private String locality;
	
	@Column
	private Integer quantity;
	
	@Column
	private String type;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_sku")
	private ProductEntity product;	
	

}
