package br.com.blz.testjava.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "WAREHOUSE", schema = "TESTJAVA")
@Entity
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6340495749893953217L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "LOCALITY", length = 100, nullable = false)
	private String locality;
	
	@Column(name = "QUANTITY", nullable = false)
	private Integer quantity;
	
	@Column(name = "TYPE", length = 100, nullable = false)
	private String type;
		
}
