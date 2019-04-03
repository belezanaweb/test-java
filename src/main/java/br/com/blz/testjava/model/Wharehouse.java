package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.com.blz.testjava.enumeration.WharehouseTypeEnum;

public class Wharehouse implements Serializable {

	private static final long serialVersionUID = -3837867431786835187L;

	private String locality;
	
	private WharehouseTypeEnum type;
	
	private Map<Product, Integer> products = new HashMap<Product, Integer>();
	
	public Wharehouse(String locality) {
		super();
		this.locality = locality;
	}

	public Wharehouse(String locality, WharehouseTypeEnum type) {
		super();
		this.locality = locality;
		this.type = type;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public WharehouseTypeEnum getType() {
		return type;
	}

	public void setType(WharehouseTypeEnum type) {
		this.type = type;
	}
	
	public Map<Product, Integer> getProducts() {
		return products;
	}
	
	public void addProduct(Product product) {
		addProduct(product, 1);
	}
	

	public void addProduct(Product product, Integer quantity) {
		Integer existQuantity = this.products.get(product);
		this.products.put(product, existQuantity == null ? quantity : existQuantity +quantity);
	}
	
	public void addAllProducts(Map<Product, Integer> products) {
		this.products = products;
	}
	
	public void removeProduct(Product product) {
		this.products.remove(product);
	}
	
	public void changeProduct(Product product, Integer quantity) {
		this.removeProduct(product);
		this.addProduct(product, quantity);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locality == null) ? 0 : locality.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wharehouse other = (Wharehouse) obj;
		if (locality == null) {
			if (other.locality != null)
				return false;
		} else if (!locality.equals(other.locality))
			return false;
		return true;
	}
	
}
