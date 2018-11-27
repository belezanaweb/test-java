package br.com.blz.testjava.gateways;

import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.domains.WarehouseTypeEntity;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.gateways.repository.ProductRepository;
import br.com.blz.testjava.gateways.repository.WarehouseTypeRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductGatewayImpl implements ProductGateway {

  private final ProductRepository productRepository;
  private final WarehouseTypeRepository warehouseTypeRepository;

  @Override
  public Product findProductBySku(Integer sku) {
    return productRepository.findBySku(sku);
  }

  @Override
  public List<Product> findAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public void createProduct(Product product) {

    product
        .getInventory()
        .getWarehouses()
        .forEach(
            warehouse -> {
              final WarehouseTypeEntity warehouseTypeEntity = warehouse.getWarehouseType();
                Optional<WarehouseTypeEntity> warehouseTypeEntityOptional = warehouseTypeRepository
                    .findByDescription(warehouse.getWarehouseType().getDescription());
              if(warehouseTypeEntityOptional.isPresent()) {
                  warehouse.setWarehouseType(warehouseTypeEntityOptional.get());
              } else {
                  warehouseTypeRepository.save(warehouseTypeEntity);
              }
            });

    productRepository.save(product);
  }

  @Override
  public void deleteProductBySku(Integer sku) {
    final Product product = findProductBySku(sku);
    if (product != null)
        productRepository.delete(product);
    else
      throw new ProductNotFoundException(
          "Product with SKU ".concat(sku.toString()).concat(" not found"));
  }
}
