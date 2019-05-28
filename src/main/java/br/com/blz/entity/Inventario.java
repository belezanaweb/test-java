package br.com.blz.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Component
@Entity(name="Inventario")
public class Inventario {

	
	private Integer id;
	private Integer quantity;
	private Set<Deposito> warehouses= new HashSet<Deposito>(0);
	private Set<Produto> produtos = new HashSet<Produto>(0);
	
	public Inventario(){
		
	}
	
	public Inventario(Integer id, Integer quantity, Set<Deposito> warehouses, Set<Produto> produtos) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.warehouses = warehouses;
		this.produtos = produtos;
	}



	@Id
	@Column(name = "ID_INVENTARIO", nullable = false, precision = 2)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "QUANTITY", nullable = false, precision = 2)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "inventario", cascade={ CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	public Set<Deposito> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(Set<Deposito> warehouses) {
		this.warehouses = warehouses;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "inventory", cascade={ CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	public Set<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
}
