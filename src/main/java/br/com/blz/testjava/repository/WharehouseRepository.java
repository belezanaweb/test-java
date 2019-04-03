package br.com.blz.testjava.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.enumeration.WharehouseTypeEnum;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Wharehouse;

@Component
public class WharehouseRepository implements Repository<Wharehouse, String> {

	private List<Wharehouse> data = new ArrayList<Wharehouse>();
	
	public WharehouseRepository() {
		for(int i=0; i<3; i++) {
			data.add(new Wharehouse("Local" + i, i%2==0 ? WharehouseTypeEnum.ECOMERCE : WharehouseTypeEnum.PHYSICAL_STORE));
		}
	}

	@Override
	public List<Wharehouse> findAll() {
		return Collections.unmodifiableList(this.data);
	}

	@Override
	public Wharehouse findById(String key) {
		if (data.isEmpty()) return null;
		
		List<Wharehouse> wharehouses = this.data.stream().filter(prod -> {
			return prod.equals(new Wharehouse(key));
		}).collect(Collectors.toList());
		
		return wharehouses != null &&  !wharehouses.isEmpty() ? wharehouses.get(0) : null;
	}

	@Override
	public void create(Wharehouse entity) {
		if (entity == null) throw new IllegalArgumentException("Wharehouse must be informed");
		this.data.add(entity);		
	}
	
	@Override
	public void update(Wharehouse entity) {
		Wharehouse ws = this.findById(entity.getLocality());
		this.delete(ws);
		this.create(ws);		
	}

	@Override
	public void delete(Wharehouse wharehouse) {
		if (wharehouse != null) this.data.remove(wharehouse);
	}

	public List<Wharehouse> findByProduct(Product product) {
		return this.data.stream().filter(wh -> {
			return wh.getProducts().containsKey(product);
		}).collect(Collectors.toList());
	}
	
	public void deleteAllProducts(Product product) {
		List<Wharehouse> wharehouses = this.findByProduct(product);
		
		if(wharehouses != null && !wharehouses.isEmpty()) {
			wharehouses.forEach(wr -> {
				wr.removeProduct(product);
			});
		}
	}	
	
}
