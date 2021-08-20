package br.com.blz.testjava.adapter_in.mapper;

import br.com.blz.testjava.adapter_in.request.ProductRequest;
import br.com.blz.testjava.adapter_in.response.ProductResponse;
import br.com.blz.testjava.application.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = IInventoryInMapper.class)
public interface IProductInMapper {

    @Mapping(target = "inventory", source = "inventoryRequest")
    Product toDomain(ProductRequest productRequest);

    @Mapping(target = "inventoryResponse", source = "inventory")
    ProductResponse toReponse(Product product);
}
