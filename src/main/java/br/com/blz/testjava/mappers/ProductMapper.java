package br.com.blz.testjava.mappers;


import br.com.blz.testjava.entities.Inventory;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.entities.WareHouse;
import br.com.blz.testjava.model.request.InventoryRequest;
import br.com.blz.testjava.model.request.ProductRequest;
import br.com.blz.testjava.model.request.WareHouseRequest;
import br.com.blz.testjava.model.response.InventoryResponse;
import br.com.blz.testjava.model.response.ProductResponse;
import br.com.blz.testjava.model.response.WareHouseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    /*
     *  ProductRequest ==> Product
     */
    WareHouse mapWareHouseRequestToWareHouse(WareHouseRequest houseHouseRequest);

    default Set<WareHouse> mapWareHousesRequestToWareHouses(Set<WareHouseRequest> wareHousesRequest) {
        return wareHousesRequest.stream().map(this::mapWareHouseRequestToWareHouse).collect(Collectors.toSet());
    }

    @Mapping(expression = "java(this.mapWareHousesRequestToWareHouses(inventoryRequest.getWarehouses()))", target = "warehouses")
    Inventory mapInventoryRequestToInventory(InventoryRequest inventoryRequest);

    @Mapping(expression = "java(this.mapInventoryRequestToInventory(productRequest.getInventory()))", target = "inventory")
    Product mapProductRequestToProduct(ProductRequest productRequest);


    /*
     *  Product ==> ProductResponse
     */
    default Set<WareHouseResponse> mapWareHousesToWareHousesResponse(Set<WareHouse> wareHouses) {
        return wareHouses.stream().map(this::mapWareHouseToWareHouseResponse).collect(Collectors.toSet());
    }

    @Mapping(expression = "java(wareHouse.getType().toString())", target = "type")
    WareHouseResponse mapWareHouseToWareHouseResponse(WareHouse wareHouse);

    @Mapping(source = "inventory.quantity", target = "quantity")
    @Mapping(expression = "java(this.mapWareHousesToWareHousesResponse(inventory.getWarehouses()))", target = "warehouses")
    InventoryResponse mapInventoryToInventoryResponse(Inventory inventory);

    @Mapping(source = "product.marketable", target = "marketable")
    @Mapping(expression = "java(this.mapInventoryToInventoryResponse(product.getInventory()))", target = "inventory")
    ProductResponse mapProductToProductResponse(Product product);

}
