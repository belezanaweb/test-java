package br.com.blz.testjava.converter.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.api.v1.model.InventoryDTO;
import br.com.blz.testjava.converter.IInventoryConverter;
import br.com.blz.testjava.converter.IWarehouseConverter;
import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Warehouse;

@Service
public class InventoryConverter implements IInventoryConverter {

	@Autowired
	private IWarehouseConverter warehouseConverter;
	
	@Override
	public Inventory toDomain(final InventoryDTO dto) {
		final InventoryDTO inventory = Optional.ofNullable(dto).orElse(InventoryDTO.builder().build());
		
		return Inventory.builder()
				.warehouses(Optional.ofNullable(inventory.getWarehouses())
						.orElse(new ArrayList<>())
						.stream()
						.map(w -> this.warehouseConverter.toDomain(w))
						.collect(Collectors.toSet()))
				.build();
	}

	@Override
	public InventoryDTO toDTO(final Inventory domain) {
		final Inventory inventory = Optional.ofNullable(domain).orElse(Inventory.builder().build());
		
		final Set<Warehouse> warehouses = Optional
				.ofNullable(inventory.getWarehouses())
				.orElse(new HashSet<>());
		
		return InventoryDTO.builder()
				.warehouses(warehouses.stream()
						.map(w -> this.warehouseConverter.toDTO(w))
						.collect(Collectors.toList()))
				.quantity(warehouses.stream()
						.collect(Collectors.summingInt(Warehouse::getQuantity)))
			.build();
	}
}