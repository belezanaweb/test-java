package br.com.blz.testjava.api.mapper;

import br.com.blz.testjava.api.inventory.entity.InventoryEntity;
import br.com.blz.testjava.api.product.controller.domain.ProductRequest;
import br.com.blz.testjava.api.product.controller.domain.ProductResponse;
import br.com.blz.testjava.api.product.dto.InventoryDto;
import br.com.blz.testjava.api.product.dto.WarehousesDto;
import br.com.blz.testjava.api.product.entity.ProductEntity;
import br.com.blz.testjava.api.werehouse.entity.WarehouseEntity;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.mapstruct.Mapper(componentModel = "spring")
@Configuration
public interface MapperUtil {

  default ProductEntity productRequestToEntity(ProductRequest productRequest) {

    if (productRequest == null) {
      return null;
    } else {
      ProductEntity productEntity = new ProductEntity();
      productEntity.setSku(productRequest.getSku());
      productEntity.setName(productRequest.getName());
      productEntity.setInventory(this.inventoryDtoToInventoryEntity(productRequest.getInventory()));

      return productEntity;
    }
  }

  default InventoryEntity inventoryDtoToInventoryEntity(InventoryDto inventoryDto) {
    if (inventoryDto == null) {
      return null;
    } else {
      final List<WarehouseEntity> warehouseEntityList = new ArrayList<>();
      InventoryEntity inventoryEntity = new InventoryEntity();
      for (WarehousesDto wh : inventoryDto.getWarehouses()) {
        warehouseEntityList.add(toEntityWareHouse(wh));
      }

      inventoryEntity.setWerehouses(warehouseEntityList);
      return inventoryEntity;
    }
  }

  default InventoryDto inventoryEntityToInventoryDto(InventoryEntity inventoryEntity) {
    if (inventoryEntity == null) {
      return null;
    } else {

      final List<WarehousesDto> warehouseDtoList = new ArrayList<>();
      InventoryDto inventoryDto = new InventoryDto();

      for (WarehouseEntity wh : inventoryEntity.getWerehouses()) {

        inventoryDto.setQuantity(
            Optional.ofNullable(inventoryDto.getQuantity()).orElse(0)
                + Optional.ofNullable(wh.getQuantity()).orElse(0));

        warehouseDtoList.add(warehousesEntityToDto(wh));
      }
      inventoryDto.setWarehouses(warehouseDtoList);
      return inventoryDto;
    }
  }

  WarehouseEntity toEntityWareHouse(WarehousesDto warehousesDto);

  WarehousesDto warehousesEntityToDto(WarehouseEntity warehousesEntity);

  default ProductResponse productEntitytoResponse(ProductEntity productEntity) {
    if (productEntity == null) {
      return null;
    } else {
      ProductResponse productResponse = new ProductResponse();
      if (productEntity.getSku() != null) {
        productResponse.setSku(productEntity.getSku().longValue());
      }
      productResponse.setName(productEntity.getName());

      productResponse.setInventory(inventoryEntityToInventoryDto(productEntity.getInventory()));
      productResponse.setIsMarketable((productResponse.getInventory().getQuantity() > 0));

      return productResponse;
    }
  }

  default ProductEntity productEntitytoEntityAtualizar(
      ProductRequest productRequest, ProductEntity productEntity) {
    if (productRequest == null) {
      return null;
    } else {

      if (productRequest.getSku() != null) {
        productEntity.setSku(productRequest.getSku());
      }
      productEntity.setName(productRequest.getName());

      productEntity.setInventory(
          inventoryDtoToInventoryEntityAtualizar(
              productRequest.getInventory(), productEntity.getInventory()));

      return productEntity;
    }
  }

  default InventoryEntity inventoryDtoToInventoryEntityAtualizar(
      InventoryDto inventoryDto, InventoryEntity inventoryEntity) {
    if (inventoryDto == null) {
      return null;
    } else {

      final List<WarehouseEntity> warehouseEntityList = new ArrayList<>();

      int size =
          Math.min(inventoryDto.getWarehouses().size(), inventoryEntity.getWerehouses().size());
      for (int i = 0; i < size; i++) {
        warehouseEntityList.add(
            toEntityWareHouseAtualizar(
                inventoryDto.getWarehouses().get(i), inventoryEntity.getWerehouses().get(i)));
      }

      inventoryEntity.setWerehouses(warehouseEntityList);
      return inventoryEntity;
    }
  }

  default WarehouseEntity toEntityWareHouseAtualizar(
      WarehousesDto warehousesDto, WarehouseEntity warehouseEntity) {
    if (warehousesDto == null) {
      return null;
    } else {
      warehouseEntity.setLocality(warehousesDto.getLocality());
      warehouseEntity.setQuantity(warehousesDto.getQuantity());
      warehouseEntity.setType(warehousesDto.getType());
      return warehouseEntity;
    }
  }
}
