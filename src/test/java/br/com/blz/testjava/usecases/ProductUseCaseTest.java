package br.com.blz.testjava.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.blz.testjava.domains.Inventory;
import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.domains.Warehouse;
import br.com.blz.testjava.domains.WarehouseType;
import br.com.blz.testjava.domains.WarehouseTypeEntity;
import br.com.blz.testjava.gateways.ProductGateway;
import br.com.blz.testjava.gateways.http.request.InventoryDTO;
import br.com.blz.testjava.gateways.http.request.ProductDTO;
import br.com.blz.testjava.gateways.http.request.WarehouseDTO;
import br.com.blz.testjava.gateways.http.response.ProductResponseDTO;
import br.com.blz.testjava.util.ValidationUtil;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductUseCaseTest {

  @InjectMocks private ProductUseCase productUseCase;

  @Mock private ProductGateway productGateway;

  @Mock private ValidationUtil validationUtil;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findAllProducts() {
    productUseCase.findAllProducts();
    verify(productGateway, times(1)).findAllProducts();
  }

  @Test
  public void findProductBySku() {
    when(productGateway.findProductBySku(1234)).thenReturn(getProduct());

    final ProductResponseDTO productResponseDTO = productUseCase.findProductBySku(1234);
    verify(productGateway, times(1)).findProductBySku(1234);
    assertThat(productResponseDTO.getInventory().getQuantity()).isEqualTo(4);
  }

  @Test
  public void deleteProductBySku() {
    productUseCase.deleteProductBySku(1234);
    verify(productGateway, times(1)).deleteProductBySku(1234);
  }

  @Test
  public void saveProduct() {
    final ProductDTO productDTO = getProductDTO();
    productUseCase.saveProduct(productDTO);

    verify(productGateway, times(1)).createProduct(any());
    verify(validationUtil, times(1)).validate(any());
  }

  private Product getProduct() {
    return Product.builder().inventory(getInventory()).name("teste").sku(1234).build();
  }

  private Inventory getInventory() {
    return Inventory.builder().quantity(4).warehouses(Arrays.asList(getWarehouse(), getWarehouse())).build();
  }

  private Warehouse getWarehouse() {
    return Warehouse.builder()
        .locality("SP")
        .quantity(2)
        .warehouseType(WarehouseTypeEntity.builder().build())
        .build();
  }

  private ProductDTO getProductDTO() {
    return ProductDTO.builder().inventory(getInventoryDTO()).name("teste").sku(123).build();
  }

  private InventoryDTO getInventoryDTO() {
    return InventoryDTO.builder()
        .warehouses(Arrays.asList(getWarehouseDTO(), getWarehouseDTO()))
        .build();
  }

  private WarehouseDTO getWarehouseDTO() {
    return WarehouseDTO.builder().locality("SP").quantity(2).type("ECOMMERCE").build();
  }
}
