package br.com.blz.testjava.Mother;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.domain.Inventory;

@Component
public class InventoryMother {

	public Inventory getInventory() {
		final Inventory domain = new Inventory();
		domain.setId(123);
		domain.setWarehouses(null);
		return domain;
	}
}
