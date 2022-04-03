package br.com.blz.testjava.converter.impl;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.api.v1.model.WarehouseDTO;
import br.com.blz.testjava.converter.IWarehouseConverter;
import br.com.blz.testjava.domain.Warehouse;

@Service
public class WarehouseConverter implements IWarehouseConverter {

	@Override
	public Warehouse toDomain(final WarehouseDTO dto) {
		return Warehouse.builder()
				.locality(dto.getLocality())
				.quantity(dto.getQuantity())
				.type(dto.getType())
				.build();
	}

	@Override
	public WarehouseDTO toDTO(final Warehouse domain) {
		return WarehouseDTO.builder()
				.locality(domain.getLocality())
				.quantity(domain.getQuantity())
				.type(domain.getType())
				.build();
	}
}