package br.com.blz.testjava;


import br.com.blz.testjava.exception.ErrorChamada;
import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.util.Util;

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
public class TestProduto {

	@LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void ProdutoNaoLocalizado_API() throws Exception {
        Integer invalidSku = 863871638;
        Produto prod = ApresentacaoProduto.criarProduto();
        prod.setSku(null); 
        HttpEntity<Produto> entity = new HttpEntity<Produto>(prod, new HttpHeaders());

        ResponseEntity<ErrorChamada> response = restTemplate.exchange(
        		Util.HTTP_LOCALHOST + port + Util.API_V1_PRODUTOS + invalidSku, HttpMethod.PUT, entity, ErrorChamada.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getError()).isEqualTo(Util.PRODUTO_NAO_ENCONTRADO + invalidSku);
    }
    
    @Test
    public void possuiRegistro_API() throws Exception {
        Produto product = ApresentacaoProduto.criarProduto();
        HttpEntity<Produto> entity = new HttpEntity<Produto>(product, new HttpHeaders());

        ResponseEntity<ErrorChamada> response = restTemplate.exchange(
          Util.HTTP_LOCALHOST + port + Util.API_V1_PRODUTOS, HttpMethod.POST, entity, ErrorChamada.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getError()).isEqualTo(Util.PRODUTO_JA_CADASTRADO);
    }
}
