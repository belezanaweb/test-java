package br.com.blz.testjava.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.dto.WarehouseDTO;

public class WarehouseMapper {

	 public static List<Warehouse> toEntities(List<WarehouseDTO> warehouseDTO) {
	        return  emptyIfNull(warehouseDTO)
	        		.stream()
	                .map(WarehouseMapper::toEntity)
	                .filter(Objects::nonNull)
	                .collect(Collectors.toList());
	 }
	 
	 
	 public static Warehouse toEntity(WarehouseDTO warehouseDTO) {
		 Warehouse warehouse = null;
 
		 warehouse = Warehouse.builder()
                    .locality(warehouseDTO.getLocality())
                    .quantity(warehouseDTO.getQuantity())
                    .type(warehouseDTO.getType())
                    .build();

	        return warehouse;
	    }
}
