package br.com.blz.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity(name="Deposito")
public class Deposito {
	
	
	private Integer id;
	private String locality;
	private Integer quantity;
	private String type;
	private Inventario inventario;
	
	public Deposito(){
		
	}

	public Deposito(Integer id, String locality, Integer quantity, String type, Inventario inventario) {
		super();
		this.id = id;
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
		this.inventario = inventario;
	}

	@Id
	@Column(name = "ID_DEPOSITO", nullable = false, precision = 2)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocality() {
		return locality;
	}

	@Column(name = "LOCALITY", length = 100)
	public void setLocality(String locality) {
		this.locality = locality;
	}

	@Column(name = "QUANTITY", nullable = false, precision = 2)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "TYPE", length = 50)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/*@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumns({@JoinColumn(name = "ID_WAREHOUSES", referencedColumnName = "ID_DEPOSITO") })
	public Inventario getInventario() {
		return this.inventario;
	}*/
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={ CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name = "ID_INVENTORY")
	public Inventario getInventario() {
		return this.inventario;
	}
	
	
	
	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}
	
}
