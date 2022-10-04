package br.com.blz.testjava.bdd;

import br.com.blz.testjava.infrastracture.api.exception.handler.ErrorResponse;
import br.com.blz.testjava.infrastracture.product.models.ProductResponse;
import br.com.blz.testjava.infrastracture.product.persistence.ProductEntity;
import br.com.blz.testjava.infrastracture.product.persistence.ProductRepository;
import br.com.blz.testjava.infrastracture.product.persistence.WarehouseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


public class ProductSteps extends CucumberSteps {

    private String json;
    private ResponseEntity<ProductResponse> productResponse;
    private ResponseEntity<String> response;

    @Autowired
    private ProductRepository repository;

    @Before
    public void setUp() {
        loadProduct();
    }

    @After
    public void tearDown() {
        deleteProduct();
    }

    @Given("criar ou editar produto:")
    public void criar_ou_editar_produto(List<String> json) {
        this.json = json.get(1);
    }

    @When("call POST \\/products")
    public void call_post_products() {
        final var headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        final var request = new HttpEntity<>(json, headers);
        final var uri = URI.create("%s:%d%s".formatted(host, port, basePath));
        response = template.exchange(uri, HttpMethod.POST, request, String.class);
    }

    @When("call PUT \\/products\\/{long}")
    public void call_put_products(Long sku) {
        final var headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        final var request = new HttpEntity<>(json, headers);
        final var uri = URI.create("%s:%d%s/%d".formatted(host, port, basePath, sku));
        productResponse = template.exchange(uri, HttpMethod.PUT, request, ProductResponse.class);
    }

    @When("call GET \\/products\\/{long}")
    public void call_get_products(Long sku) {
        final var headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        final var request = new HttpEntity<>(json, headers);
        final var uri = URI.create("%s:%d%s/%d".formatted(host, port, basePath, sku));
        productResponse = template.exchange(uri, HttpMethod.GET, request, ProductResponse.class);
    }

    @When("call DELETE \\/products\\/{long}")
    public void call_delete_products(Long sku) {
        final var headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        final var request = new HttpEntity<>(json, headers);
        final var uri = URI.create("%s:%d%s/%d".formatted(host, port, basePath, sku));
        response = template.exchange(uri, HttpMethod.DELETE, request, String.class);
    }

    @Then("status response {int}")
    public void status_response(Integer status) {
        Assertions.assertEquals(status, response.getStatusCode().value());
    }

    @Then("status {int}")
    public void status(Integer status) {
        Assertions.assertEquals(status, productResponse.getStatusCode().value());
    }

    @Then("sku {long}")
    public void sku(Long sku) {
        Assertions.assertNotNull(productResponse.getBody().sku());
        Assertions.assertEquals(sku, productResponse.getBody().sku());
    }

    @Then("inventory.quantity {long}")
    public void inventory_quantity(Long quantity) {
        Assertions.assertNotNull(productResponse.getBody().inventory());
        Assertions.assertEquals(quantity, productResponse.getBody().inventory().quantity());
    }

    @Then("isMarketable {string}")
    public void is_marketable(String isMarketable) {
        Assertions.assertNotNull(productResponse.getBody().isMarketable());
        Assertions.assertEquals(Boolean.valueOf(isMarketable), productResponse.getBody().isMarketable());
    }

    @Then("error {string}")
    public void error(String error) throws JsonProcessingException {
        final var objectMapper = new ObjectMapper();
        final var errorResponse = objectMapper.readValue(response.getBody(), ErrorResponse.class);
        Assertions.assertEquals(error, errorResponse.message());
    }

    private void loadProduct() {
        final var sku43260 = 43260L;
        final var sku43261 = 43261L;
        final var name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g";
        final var warehouses = Arrays.asList(WarehouseEntity.builder().locality("SP").quantity(0).type("ECOMMERCE").build(),
            WarehouseEntity.builder().locality("MOEMA").quantity(0).type("PHYSICAL_STORE").build());

        repository.save(ProductEntity.builder().sku(sku43260).name(name).warehouses(null).build());
        repository.save(ProductEntity.builder().sku(sku43261).name(name).warehouses(warehouses).build());
    }

    private void deleteProduct() {
        repository.delete(43260L);
        repository.delete(43261L);
    }
}
