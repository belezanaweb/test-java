package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WareHouseDTO;
import br.com.blz.testjava.exceptions.ExistentProductException;
import br.com.blz.testjava.exceptions.InexistentProductException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private String PRODUCT_NOT_FOUND = "Não encontramos o SKU específicado...";
    private String PRODUCT_ALREADY_EXISTS = "já existe um produto com o SKU informado.";

    @Autowired
    ProductRepository productRepository;

    List<Product> serviceProducts;

    @Override
    public Product createProduct(ProductDTO product) {
        logger.info(String.format("Criando produto %s", product));

        recoverProductsFromRepository();
        checkProductAlreadyRegistred(product.getSku());

        List<Warehouse> newWarrehouses =
            mapWareHouses(product.getInventory().getWarehouses());

        Inventory newInventory = buildInventory(newWarrehouses);

        boolean isMarketable = newInventory.getQuantity() > 0;

        Product newProduct =
            Product.builder()
                .sku(product.getSku())
                .name(product.getName())
                .inventory(newInventory)
                .isMarketable(isMarketable)
                .build();

        serviceProducts.add(newProduct);
        productRepository.setProducts(serviceProducts);

        logger.info(String.format("produto criado: %s ", newProduct));
        return newProduct;
    }

    @Override
    public Product findProductBySKU(Integer sku) throws RuntimeException {
        logger.info(String.format("Procurando produto com sku %s", sku));
        recoverProductsFromRepository();
        Optional<Product> searchProduct = serviceProducts.stream().filter(product -> product.getSku().equals(sku)).findAny();
        return searchProduct.orElseThrow(() -> new InexistentProductException(PRODUCT_NOT_FOUND));
    }

    @Override
    public Product editProduct(Integer sku,
                               ProductDTO product) {
        logger.info(String.format("Editando produto com sku %s", sku));

        recoverProductsFromRepository();
        Product oldProduct = findProductBySKU(sku);

        List<Warehouse> newWarrehouses =
            mapWareHouses(product.getInventory().getWarehouses());

        Inventory newInventory = buildInventory(newWarrehouses);

        boolean isMarketable = newInventory.getQuantity() > 0;

        Product newProduct = Product.builder()
            .sku(product.getSku())
            .name(product.getName())
            .inventory(newInventory)
            .isMarketable(isMarketable)
            .build();

        deleteProductBySku(product.getSku());
        serviceProducts.add(newProduct);
        updateProductsFromRepository();

        return newProduct;
    }

    @Override
    public void deleteProductBySku(Integer sku) {
        logger.info(String.format("Deletando produto com sku %s", sku));
        recoverProductsFromRepository();
        Product productToDelete = findProductBySKU(sku);
        serviceProducts.remove(productToDelete);
        updateProductsFromRepository();
    }

    protected void recoverProductsFromRepository(){
        this.serviceProducts =
            Optional.ofNullable(productRepository.getProducts()).orElse(new ArrayList<>());
    }

    protected void updateProductsFromRepository(){
        productRepository.setProducts(serviceProducts);
    }

    protected void checkProductAlreadyRegistred(Integer sku) throws RuntimeException{
        List<Product> produtosExistente =
            serviceProducts.stream()
                .filter(product -> product.getSku().equals(sku))
                .collect(Collectors.toList());
        if(! produtosExistente.isEmpty())
            throw new ExistentProductException(PRODUCT_ALREADY_EXISTS);
    }

    protected List<Warehouse> mapWareHouses(List<WareHouseDTO> wareHouseDTOS) {
        logger.info(String.format("Mapeando warehouses a partir de %s", wareHouseDTOS));

        return wareHouseDTOS.stream().map(wareHouseDTO -> Warehouse.builder()
            .locality(wareHouseDTO.getLocality())
            .quantity(wareHouseDTO.getQuantity())
            .type(wareHouseDTO.getType())
            .build()).collect(Collectors.toList());
    }

    protected Inventory buildInventory(List<Warehouse> warehouses ){
        logger.info(String.format("Construindo Inventory a partir de %s", warehouses));

        List<Integer> quantities = warehouses.stream().map(Warehouse::getQuantity).collect(Collectors.toList());
        int quantity = quantities.stream().reduce(0, Integer::sum);

        Inventory newInventory = Inventory.builder()
            .quantity(quantity)
            .warehouses(warehouses)
            .build();
        logger.info(String.format("Construido Inventory %s", newInventory));
        return newInventory;
    }
}
