package br.com.blz.testjava.adapter_in.mapper;

import br.com.blz.testjava.adapter_in.request.WareHousesRequest;
import br.com.blz.testjava.adapter_in.response.WareHousesResponse;
import br.com.blz.testjava.application.domain.WareHouses;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IWareHousesInMapper {

    List<WareHouses> toDomainList(List<WareHousesRequest> wareHousesRequestList);
    WareHouses toDomain(WareHousesRequest wareHousesRequest);

    List<WareHousesResponse> toReponseList(List<WareHouses> wareHousesList);
    WareHousesResponse toReponse(WareHouses wareHouses);
}
