package br.com.blz.testjava.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "PRODUTO", schema = "TESTJAVA")
@Entity
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8670496649878583542L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "SKU", length = 50, nullable = false, unique = true)
	private String sku;
	
	@Column(name = "NAME", length = 100, nullable = false)
	private String name;
	
	@JoinColumn(unique = true)
	@OneToOne(cascade = {CascadeType.ALL})
	private Inventory inventory;
	
	@Column(name = "IS_MARKETABLE")
	private Boolean isMarketable;

}
