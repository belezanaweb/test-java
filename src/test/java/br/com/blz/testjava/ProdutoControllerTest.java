package br.com.blz.testjava;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JpaRepository repository;


    @Test
    public void should_RecuperProdutoPorSku() {

        final long sku = 123L;

        Produto produtoPorSku = new Produto(sku,"produto por sku");
        repository.saveAndFlush(produtoPorSku);

        ResponseEntity<Produto> responseEntity = restTemplate.getForEntity("/produtos/" + sku, Produto.class);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(produtoPorSku, responseEntity.getBody());
    }
}
