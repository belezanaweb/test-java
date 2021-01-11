package br.com.blz.testjava.mapper;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import br.com.blz.testjava.dto.WareHouseDTO;
import br.com.blz.testjava.model.WareHouse;

public class WareHousesMapper {

	private WareHousesMapper() {
	}

	public static Vector<WareHouse> toEntities(List<WareHouseDTO> whireHouses) {

		return whireHouses.stream().map(whireHouseDTO -> toEntity(whireHouseDTO))
				.collect(Collectors.toCollection(Vector::new));
	}

	public static WareHouse toEntity(WareHouseDTO whireHouseDTO) {

		WareHouse whireHouse = new WareHouse();
		whireHouse.setLocality(whireHouseDTO.getLocality());
		whireHouse.setQuantity(whireHouseDTO.getQuantity());
		whireHouse.setType(whireHouseDTO.getType());

		return whireHouse;
	}

	public static Vector<WareHouseDTO> toDTOS(Vector<WareHouse> whireHouses) {

		return whireHouses.stream().map(whireHouse -> toDTO(whireHouse)).collect(Collectors.toCollection(Vector::new));
	}

	private static WareHouseDTO toDTO(WareHouse whireHouse) {

		WareHouseDTO dto = new WareHouseDTO();
		dto.setLocality(whireHouse.getLocality());
		dto.setQuantity(whireHouse.getQuantity());
		dto.setType(whireHouse.getType());

		return dto;
	}

}
