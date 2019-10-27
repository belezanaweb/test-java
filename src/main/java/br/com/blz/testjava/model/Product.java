/**
 * 
 */
package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author ocean
 *
 */
@Entity
@Table(name="Product")
public class Product implements Serializable{

	private static final long serialVersionUID = 1954716593942272416L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sku")
	private Long sku;

	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private	List<Warehouses> warehouses;	

	/**
	 * @return the sku
	 */
	public Long getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(Long sku) {
		this.sku = sku;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the warehouses
	 */
	public List<Warehouses> getWarehouses() {
		if(this.warehouses == null){
			this.warehouses = new ArrayList<>(); 
		}
		return warehouses;
	}

	/**
	 * @param warehouses the warehouses to set
	 */
	public void setWarehouses(List<Warehouses> warehouses) {
		this.warehouses = warehouses;
	}

	
	/**
	 * Adds the warehouses.
	 *
	 * @param warehouses the warehouses
	 * @return the warehouses
	 */
	public Warehouses addWarehouses(Warehouses warehouses) {
		getWarehouses().add(warehouses);
		warehouses.setProduct(this);

		return warehouses;
	}

	/**
	 * Removes the warehouses.
	 *
	 * @param warehouses the warehouses
	 * @return the warehouses
	 */
	public Warehouses removeWarehouses(Warehouses warehouses) {
		getWarehouses().remove(warehouses);
		warehouses.setProduct(null);

		return warehouses;
	}
	
}