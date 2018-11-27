package br.com.blz.testjava.usecases;

import br.com.blz.testjava.domains.Inventory;
import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.domains.Warehouse;
import br.com.blz.testjava.domains.WarehouseType;
import br.com.blz.testjava.domains.WarehouseTypeEntity;
import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.gateways.ProductGateway;
import br.com.blz.testjava.gateways.http.request.InventoryDTO;
import br.com.blz.testjava.gateways.http.request.ProductDTO;
import br.com.blz.testjava.gateways.http.request.WarehouseDTO;
import br.com.blz.testjava.gateways.http.response.InventoryResponseDTO;
import br.com.blz.testjava.gateways.http.response.ProductResponseDTO;
import br.com.blz.testjava.gateways.http.response.WarehouseResponseDTO;
import br.com.blz.testjava.util.ValidationUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductUseCase {

  private final ProductGateway productGateway;
  private final ValidationUtil validationUtil;

  public List<ProductResponseDTO> findAllProducts() {
    return buildProductsResponse(productGateway.findAllProducts());
  }

  public ProductResponseDTO findProductBySku(Integer sku) {
    return buildProductResponse(productGateway.findProductBySku(sku));
  }

  public Product findProductBySku(ProductDTO productDTO) {
    return productGateway.findProductBySku(productDTO.getSku());
  }

  public void deleteProductBySku(Integer sku) {
    productGateway.deleteProductBySku(sku);
  }

  public void saveProduct(ProductDTO productDTO) {
    validationUtil.validate(productDTO);
    final Product product = findProductBySku(productDTO);

    if (productDTO.getId() != null && productDTO.getId() != 0) {
      if (product == null) {
        throw new ProductNotFoundException(
            "Product with SKU ".concat(productDTO.getSku().toString()).concat(" not found"));
      }
    } else {
      if (product != null) {
        throw new ProductAlreadyExistsException(
            "Error on create a new Product, product with sku "
                .concat(productDTO.getSku().toString())
                .concat(" already exists"));
      }
    }

    final Inventory inventory = buildInventory(productDTO.getInventory());
    productGateway.createProduct(
        Product.builder()
            .id(productDTO.getId())
            .name(productDTO.getName())
            .sku(productDTO.getSku())
            .isMarketable(inventory.getQuantity() > 0 ? true : false)
            .inventory(inventory)
            .build());
  }

  private Inventory buildInventory(InventoryDTO inventoryDTO) {
    return Inventory.builder()
        .quantity(getTotalProductsQuantity(inventoryDTO.getWarehouses()))
        .warehouses(buildWarehouses(inventoryDTO.getWarehouses()))
        .build();
  }

  private int getTotalProductsQuantity(List<WarehouseDTO> warehouseDTOS) {
    return warehouseDTOS
        .stream()
        .map(WarehouseDTO::getQuantity)
        .reduce((integer, integer2) -> integer + integer2)
        .orElse(0);
  }

  private List<Warehouse> buildWarehouses(List<WarehouseDTO> warehouseDTOS) {
    return warehouseDTOS
        .stream()
        .map(
            warehouseDTO ->
                Warehouse.builder()
                    .locality(warehouseDTO.getLocality())
                    .quantity(warehouseDTO.getQuantity())
                    .warehouseType(
                        WarehouseTypeEntity.builder()
                            .description(
                                WarehouseType.ECOMMERCE.toString().equals(warehouseDTO.getType())
                                    ? WarehouseType.ECOMMERCE.toString()
                                    : WarehouseType.PHYSICAL_STORE.toString())
                            .build())
                    .build())
        .collect(Collectors.toList());
  }

  private List<ProductResponseDTO> buildProductsResponse(List<Product> products) {
    return products
        .stream()
        .map(
            product ->
                ProductResponseDTO.builder()
                    .id(product.getId())
                    .inventory(buildInventoryResponse(product.getInventory()))
                    .isMarketable(product.isMarketable())
                    .name(product.getName())
                    .sku(product.getSku())
                    .build())
        .collect(Collectors.toList());
  }

  private ProductResponseDTO buildProductResponse(Product product) {
    return ProductResponseDTO.builder()
        .id(product.getId())
        .inventory(buildInventoryResponse(product.getInventory()))
        .isMarketable(product.isMarketable())
        .name(product.getName())
        .sku(product.getSku())
        .build();
  }

  private InventoryResponseDTO buildInventoryResponse(Inventory inventory) {
    return InventoryResponseDTO.builder()
        .quantity(inventory.getQuantity())
        .warehouses(buildWarehouseResponse(inventory.getWarehouses()))
        .build();
  }

  private List<WarehouseResponseDTO> buildWarehouseResponse(List<Warehouse> warehouses) {
    return warehouses
        .stream()
        .map(
            warehouse ->
                WarehouseResponseDTO.builder()
                    .warehouseType(
                        WarehouseType.ECOMMERCE
                                .toString()
                                .equals(warehouse.getWarehouseType().getDescription())
                            ? WarehouseType.ECOMMERCE.toString()
                            : WarehouseType.PHYSICAL_STORE.toString())
                    .quantity(warehouse.getQuantity())
                    .locality(warehouse.getLocality())
                    .build())
        .collect(Collectors.toList());
  }
}
