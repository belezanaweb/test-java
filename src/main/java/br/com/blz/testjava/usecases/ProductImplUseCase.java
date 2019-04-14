package br.com.blz.testjava.usecases;

import br.com.blz.testjava.dto.request.InventoryDTO;
import br.com.blz.testjava.dto.request.ProductRequestDTO;
import br.com.blz.testjava.dto.request.TypeInventoryDTO;
import br.com.blz.testjava.dto.request.WarehouseDTO;
import br.com.blz.testjava.dto.response.ProductResponseDTO;
import br.com.blz.testjava.exception.ExistingProductException;
import br.com.blz.testjava.exception.GenericErrorException;
import br.com.blz.testjava.exception.NotFoundProductException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.TypeInventory;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.services.ValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Slf4j
@AllArgsConstructor
@Component
public class ProductImplUseCase implements ProductUseCase {

    private ProductRepository productRepository;
    private ValidationService validationService;

    @Override
    public void create(ProductRequestDTO body) {
        log.info("Begin ProductImplUseCase : create : body : " + body.toString());
        validationService.validate(body);
        Product product = this.convertProductRequestDTOToProduct(body);
        boolean existsProduct = productRepository.existsBySku(product.getSku());
        if (nonNull(existsProduct) && !existsProduct) {
            productRepository.save(product);
        } else if(nonNull(existsProduct) && existsProduct){
            throw new ExistingProductException();
        } else {
            throw new GenericErrorException();
        }
        log.info("End ProductImplUseCase : create : body : " + body.toString());
    }

    @Override
    public void delete(Integer sku) {
        log.info("Begin ProductImplUseCase : delete : sku : " + sku.toString());
        if(nonNull(this.getBySku(sku))) {
            productRepository.delete(sku);
        }
        log.info("End ProductImplUseCase : delete : sku : " + sku.toString());
    }

    @Override
    public ProductResponseDTO getBySku(Integer sku) {
        log.info("Begin ProductImplUseCase : getBySku : sku : " + sku.toString());
        Product product = Optional.ofNullable(productRepository.findOne(sku))
            .orElseThrow(() -> new NotFoundProductException());
        ProductResponseDTO productResponseDTO = this.convertProductToProductResponseDTO(product);
        productResponseDTO.getInventory().setQuantity(
            this.sum(product.getInventory().getWarehouses())
        );
        boolean isMarketable = productResponseDTO.getInventory().getQuantity() > 0;
        productResponseDTO.setMarketable(isMarketable);
        log.info("End ProductImplUseCase : getBySku : sku : " + sku.toString() + "response: " + productResponseDTO.toString());
        return productResponseDTO;
    }

    @Override
    public void update(ProductRequestDTO body, Integer sku) {
        log.info("Begin ProductImplUseCase : update : body : " + body.toString() + " : sku: " + sku);
        body.setSku(sku);
        validationService.validate(body);
        Product product = this.convertProductRequestDTOToProduct(body);
        try {
            Product productDB = Optional.ofNullable(productRepository.findOne(body.getSku()))
                .orElseThrow(() -> new NotFoundProductException());
            product.setInventory(product.getInventory());
            product.setName(product.getName());
            product.setInventory(product.getInventory());
            productRepository.save(product);
        } catch (NotFoundProductException ex) {
            throw new NotFoundProductException();
        } catch (Exception ex) {
            throw new GenericErrorException();
        }
        log.info("End ProductImplUseCase : update : body : " + body.toString() + " : sku: " + sku);
    }

    private ProductResponseDTO convertProductToProductResponseDTO(Product product) {
        log.info("Begin ProductImplUseCase : convertProductToProductResponseDTO : product : " + product.toString());
        List<WarehouseDTO> warehouseList = new ArrayList<>();
        for (Warehouse wdto : product.getInventory().getWarehouses()) {
            TypeInventoryDTO typeInventory = null;
            if (wdto.getType().name().equals(TypeInventory.ECOMMERCE.name())) {
                typeInventory = TypeInventoryDTO.ECOMMERCE;
            } else if (wdto.getType().name().equals(TypeInventory.PHYSICAL_STORE.name())) {
                typeInventory = TypeInventoryDTO.PHYSICAL_STORE;
            }
            WarehouseDTO wh = WarehouseDTO.builder()
                .locality(wdto.getLocality())
                .quantity(wdto.getQuantity())
                .type(typeInventory)
                .build();
            warehouseList.add(wh);
        }
        InventoryDTO inventory = InventoryDTO.builder()
            .quantity(product.getInventory()
                .getQuantity())
                .warehouses(warehouseList).build();
        log.info("End ProductImplUseCase : convertProductToProductResponseDTO : product : " + product.toString());
        return ProductResponseDTO
            .builder()
            .sku(product.getSku())
            .name(product.getName())
            .marketable(product.getMarketable())
            .inventory(inventory)
            .build();
    }

    private Product convertProductRequestDTOToProduct(ProductRequestDTO productRequestDTO) {
        log.info("Begin ProductImplUseCase : convertProductToProductResponseDTO : productRequestDTO : " + productRequestDTO.toString());
        List<Warehouse> warehouseList = new ArrayList<>();
        for (WarehouseDTO wdto : productRequestDTO.getInventory().getWarehouses()) {
            TypeInventory typeInventory = null;
            if (wdto.getType().name().equals(TypeInventory.ECOMMERCE.name())) {
                typeInventory = TypeInventory.ECOMMERCE;
            } else if (wdto.getType().name().equals(TypeInventory.PHYSICAL_STORE.name())) {
                typeInventory = TypeInventory.PHYSICAL_STORE;
            }
            Warehouse wh = Warehouse.builder()
                .locality(wdto.getLocality())
                .quantity(wdto.getQuantity())
                .type(typeInventory)
                .build();
            warehouseList.add(wh);
        }
        Inventory inventory = Inventory.builder()
            .quantity(productRequestDTO.getInventory()
                .getQuantity())
            .warehouses(warehouseList).build();
        log.info("End ProductImplUseCase : convertProductToProductResponseDTO : productRequestDTO : " + productRequestDTO.toString());
        return Product
            .builder()
            .sku(productRequestDTO.getSku())
            .name(productRequestDTO.getName())
            .inventory(inventory)
            .build();
    }

    public int sum(List<Warehouse> warehouses) {
        return warehouses
            .stream()
            .filter(warehouse -> warehouse.getQuantity() > 0)
            .mapToInt(Warehouse::getQuantity)
            .sum();
    }
}
