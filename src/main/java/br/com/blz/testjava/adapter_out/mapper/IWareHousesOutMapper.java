package br.com.blz.testjava.adapter_out.mapper;

import br.com.blz.testjava.adapter_out.entity.WareHousesEntity;
import br.com.blz.testjava.application.domain.WareHouses;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IWareHousesOutMapper {

    List<WareHousesEntity> toEntityList(List<WareHouses> wareHousesList);
    WareHousesEntity toEntity(WareHouses wareHouses);

    List<WareHouses> toDomainList(List<WareHousesEntity> wareHousesEntityList);
    WareHouses toDomain(WareHousesEntity wareHousesEntity);
}
