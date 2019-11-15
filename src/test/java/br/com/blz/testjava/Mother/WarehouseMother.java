package br.com.blz.testjava.Mother;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.domain.Warehouse;

@Component
public class WarehouseMother {

	public Warehouse getWarehouse() {
		final Warehouse domain = new Warehouse();
		domain.setId(12345);
		domain.setLocality("javali");
		domain.setQuantity(7890);
		domain.setType("webbbb");

		return domain;
	}
}