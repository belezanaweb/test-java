package br.com.blz.testjava.adapter_in.mapper;

import br.com.blz.testjava.adapter_in.request.InventoryRequest;
import br.com.blz.testjava.adapter_in.response.InventoryResponse;
import br.com.blz.testjava.application.domain.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = IWareHousesInMapper.class)
public interface IInventoryInMapper {

    @Mapping(target = "wareHousesList", source = "wareHousesRequestList")
    Inventory toDomain(InventoryRequest inventoryRequest);

    @Mapping(target = "wareHousesResponseList", source = "wareHousesList")
    InventoryResponse toReponse(Inventory inventory);
}
