package br.com.blz.testjava.mother;

import br.com.blz.testjava.domain.request.InventoryRequest;
import br.com.blz.testjava.domain.request.ProductCreateRequest;
import br.com.blz.testjava.domain.request.ProductUpdateRequest;
import br.com.blz.testjava.domain.response.ProductResponse;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;

import static br.com.blz.testjava.mother.InventoryMother.getInventory;
import static br.com.blz.testjava.mother.InventoryMother.getInventoryRequest;
import static br.com.blz.testjava.mother.InventoryMother.getInventoryResponse;

public class ProductMother {

    private ProductMother() {
    }

    public static Product getProduct() {
        Product product = new Product();
        product.setSku(43264L);
        product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
        product.setInventory(getInventory());
        return product;
    }

    public static Product getProduct(Long sku) {
        Product product = getProduct();
        product.setSku(sku);
        return product;
    }

    public static Product getProduct(String name, Inventory inventory) {
        Product product = getProduct();
        product.setName(name);
        product.setInventory(inventory);
        return product;
    }

    public static ProductCreateRequest getProductCreateRequest() {
        ProductCreateRequest productCreateRequest = new ProductCreateRequest();
        productCreateRequest.setSku(43264L);
        productCreateRequest.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
        productCreateRequest.setInventory(getInventoryRequest());
        return productCreateRequest;
    }

    public static ProductUpdateRequest getProductUpdateRequest() {
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest();
        productUpdateRequest.setName("Name Update");
        productUpdateRequest.setInventory(getInventoryRequest());
        return productUpdateRequest;
    }

    public static ProductUpdateRequest getProductUpdateRequest(InventoryRequest inventory) {
        ProductUpdateRequest productUpdateRequest = getProductUpdateRequest();
        productUpdateRequest.setInventory(inventory);
        return productUpdateRequest;
    }

    public static ProductResponse getProductResponse() {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
        productResponse.setSku(43264L);
        productResponse.setInventory(getInventoryResponse());
        return productResponse;
    }
}
