package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repository;

    @Autowired
    private WarehouseService warehouseService;

    public void createOrUpdate(ProductDto productDto) throws Exception {
        LOGGER.info("ProductService:Localizando sku:{}", productDto.getSku());
        Optional<Product> product = repository.findById(productDto.getSku());

        if (product.isPresent()) {
            updateProduct(productDto, product.get());
        } else {
            createProduct(productDto);
        }

        LOGGER.info("ProductService:Finalizado createOrUpdate sku:{}", productDto.getSku());
    }

    public void create(ProductDto productDto) throws Exception {
        if (repository.existsById(productDto.getSku())) {
            throw new Exception("Produto já existente");
        }

        createProduct(productDto);
    }

    private void createProduct(ProductDto productDto) {
        LOGGER.info("ProductService:Create sku:{}", productDto.getSku());
        Product product = repository.save(new Product(productDto));
        warehouseService.createWarehouse(product, productDto.getInventory());
        LOGGER.info("ProductService:Criação com sucesso sku:{}", productDto.getSku());
    }

    public void delete(Long sku) throws Exception {
        LOGGER.info("ProductService:Deletando sku:{}", sku);
        Optional<Product> product = repository.findById(sku);
        if (product.isPresent()) {
            delete(product.get());
        } else {
            LOGGER.info("ProductService:Não existente para deleção sku:{}", sku);
            throw new Exception("Registro inexistente");
        }
        LOGGER.info("ProductService:Sucesso delete sku:{}", sku);
    }

    private void delete(Product product) {
        LOGGER.info("ProductService:Deletando sku:{}", product.getSku());
        warehouseService.removeBySku(product);
        repository.delete(product);
        LOGGER.info("ProductService:Sucesso delete sku:{}", product.getSku());
    }

    private void updateProduct(ProductDto productDto, Product product) {
        LOGGER.info("ProductService:Update sku:{}", productDto.getSku());
        delete(product);
        LOGGER.info("ProductService:Remoção do produto atual com sucesso. sku:{}", productDto.getSku());
        createProduct(productDto);
    }

    public ProductDto retriver(Long sku) throws Exception {
        Product product = getProduct(sku);
        List<Warehouse> warehouses = warehouseService.getWarehouses(product);

        InventoryDto inventoryDto = new InventoryDto(warehouses);
        return new ProductDto(product, inventoryDto);
    }

    private Product getProduct(Long sku) {
        Optional<Product> product = repository.findById(sku);
        return product.isPresent() ? product.get() : null;
    }
}
