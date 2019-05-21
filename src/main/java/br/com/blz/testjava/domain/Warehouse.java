package br.com.blz.testjava.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "WAREHOUSE")
public class Warehouse {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "locality")
	private String locality;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "type")
	private String type;

}
