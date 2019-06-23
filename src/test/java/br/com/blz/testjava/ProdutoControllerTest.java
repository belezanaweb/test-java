package br.com.blz.testjava;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Produto;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.domain.WarehouseType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() throws Exception {
        mongoTemplate.dropCollection("produto");
    }

    @Test
    public void deve_RecuperarProdutoPorSku_comSkuValido() {

        final long sku = 123L;

        Produto produtoExpected = salvarProdutoNoBanco(sku);

        ResponseEntity<Produto> responseEntity = restTemplate.getForEntity("/produtos/" + sku, Produto.class);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Produto produto = responseEntity.getBody();
        assertEquals(produtoExpected, produto);

        Inventory inventory = produto.getInventory();
        assertNotNull(inventory);
        assertTrue(inventory.getQuantity() > 0);
        List<Warehouse> warehouses = inventory.getWarehouses();
        Warehouse warehouse = warehouses.get(0);
        assertNotNull(warehouse);
        assertNotNull(warehouse.getLocality());
        assertTrue(warehouse.getQuantity() > 0);
        assertNotNull(warehouse.getType());
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

        List<Warehouse> warehouses = new ArrayList<Warehouse>();
        warehouses.add(new Warehouse("SP",12, WarehouseType.ECOMMERCE));
        Inventory inventory = new Inventory(15,warehouses);

        produto.setInventory(inventory);
        mongoTemplate.save(produto);
        return produto;
    }
}
