package br.com.blz.testjava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Inventory {

    @Id
    @GeneratedValue
    @JsonIgnore
    @Column(name = "codigo")
    private long codigo;


    @Column(name = "locality")
    private long quantity;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<Warehouses> warehouses;

    @JsonIgnore
    @OneToOne
    private Produto produto;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public List<Warehouses> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouses> warehouses) {
		this.warehouses = warehouses;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
    
    
}
