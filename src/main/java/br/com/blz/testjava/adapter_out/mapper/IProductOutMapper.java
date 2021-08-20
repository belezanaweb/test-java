package br.com.blz.testjava.adapter_out.mapper;

import br.com.blz.testjava.adapter_out.entity.ProductEntity;
import br.com.blz.testjava.application.domain.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = IInventoryOutMapper.class)
public interface IProductOutMapper {

    @Mapping(target = "inventoryEntity", source = "inventory")
    ProductEntity toEntity(Product product);

    @InheritInverseConfiguration
    Product toDomain(ProductEntity productEntity);
}
