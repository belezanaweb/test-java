package br.com.blz.testjava;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MongoRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void deve_RecuperarProdutoPorSku_comSkuValido() {

        final long sku = 123L;

        Produto produtoPorSku = salvarProdutoNoBanco(sku);

        ResponseEntity<Produto> responseEntity = restTemplate.getForEntity("/produtos/" + sku, Produto.class);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(produtoPorSku, responseEntity.getBody());
    }

    @Test
    public void deve_RetonarProdutoNaoEncontrado_comSkuInvalido() {

        final long sku = 123L;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/produtos/" + sku, String.class);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
        assertEquals("prodouto (sku="+sku+") não encontrado",responseEntity.getBody());
    }

    private Produto salvarProdutoNoBanco(long sku) {
        Produto produto = new Produto(sku,"produto salvo no banco");
        repository.save(produto);
        return produto;
    }
}
