package br.com.blz.testjava;


import br.com.blz.testjava.exception.ErrorResponse;
import br.com.blz.testjava.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestIntegrationProductApi {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void product_post_api_with_same_sku() throws Exception {
        Product product = DataSeed.createProduct();
        HttpEntity<Product> entity = new HttpEntity<Product>(product, new HttpHeaders());

        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
          "http://localhost:" + port + "/api/v1/products", HttpMethod.POST, entity, ErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getError()).isEqualTo("Produto já cadastrado");
    }

    @Test
    public void product_put_api_with_invalid_sku() throws Exception {
        Integer invalidSku = 863871638;
        Product product = DataSeed.createProduct();
        product.setSku(null); //sku must be null on update
        HttpEntity<Product> entity = new HttpEntity<Product>(product, new HttpHeaders());

        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
          "http://localhost:" + port + "/api/v1/products/" + invalidSku, HttpMethod.PUT, entity, ErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getError()).isEqualTo("Product with sku not found " + invalidSku);
    }
}
