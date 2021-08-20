package br.com.blz.testjava.adapter_out.mapper;

import br.com.blz.testjava.adapter_out.entity.InventoryEntity;
import br.com.blz.testjava.application.domain.Inventory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = IWareHousesOutMapper.class)
public interface IInventoryOutMapper {

    @Mapping(target = "wareHousesEntityList", source = "wareHousesList")
    InventoryEntity toEntity(Inventory inventory);

    @InheritInverseConfiguration
    Inventory toDomain(InventoryEntity inventoryEntity);
}
