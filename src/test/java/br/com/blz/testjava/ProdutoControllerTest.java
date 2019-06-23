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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void deve_AtualizarProdutoExistente_comSkuValido() {

        final long sku = 43264;
        Produto produto = salvarProdutoNoBanco(sku);
        produto = new Produto(sku,"Alterei o nome do produto");

        Map<String, String> params = new HashMap<String, String>();
        params.put("sku", String.valueOf(sku));

        restTemplate.put("/produtos/{sku}",produto,params);

        assertAtualizouNomeDoProduto(sku, produto, "Alterei o nome do produto");
    }

    @Test
    public void deve_RetonarProdutoJaExisteBadRequest_comSkuRepetido() {

        Produto produto = salvarProdutoNoBanco(43264);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/produtos/", produto, String.class);

        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
        assertEquals("produto (sku="+produto.getSku()+") já existe na base",responseEntity.getBody());
    }

    @Test
    public void deve_CriarProduto_comSkuValido() {

        Produto produto = new Produto(43264,"deve_CriarProduto_comSkuValido");
        Inventory inventory = new Inventory(getWarehouses());
        produto.setInventory(inventory);

        ResponseEntity<Produto> responseEntity = restTemplate.postForEntity("/produtos/", produto, Produto.class);

        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        assertEquals(produto,responseEntity.getBody());
    }

    @Test
    public void deve_RecuperarProdutoPorSku_comSkuValido() {

        final long sku = 123L;
        Produto produtoExpected = salvarProdutoNoBanco(sku);

        ResponseEntity<Produto> responseEntity = restTemplate.getForEntity("/produtos/" + sku, Produto.class);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Produto produto = responseEntity.getBody();
        assertEquals(produtoExpected, produto);
        assertRetornouInformacoes(produto);
    }

    @Test
    public void deve_RetonarProdutoNaoEncontrado_comSkuInvalido() {

        final long sku = 43264L;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/produtos/" + sku, String.class);

        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
        assertEquals("produto (sku="+sku+") não encontrado",responseEntity.getBody());
    }

    private void assertAtualizouNomeDoProduto(long sku, Produto produto, String newName) {
        Produto produtoDoBanco = mongoTemplate.findById(sku, Produto.class);
        assertEquals(produto,produtoDoBanco);
        assertEquals(newName,produtoDoBanco.getName());
    }

    private void assertRetornouInformacoes(Produto produto) {
        assertNotNull(produto.getName());
        Inventory inventory = produto.getInventory();
        assertNotNull(inventory);
        List<Warehouse> warehouses = inventory.getWarehouses();
        Warehouse warehouse0 = warehouses.get(0);
        assertNotNullWarehouse(warehouse0);
        Warehouse warehouse1 = warehouses.get(1);
        assertNotNullWarehouse(warehouse1);
        assertEquals((warehouse0.getQuantity() + warehouse1.getQuantity() ),inventory.getQuantity());
        assertTrue(produto.isMarketable());
    }

    private void assertNotNullWarehouse(Warehouse warehouse) {
        assertNotNull(warehouse);
        assertNotNull(warehouse.getLocality());
        assertTrue(warehouse.getQuantity() > 0);
        assertNotNull(warehouse.getType());
    }

    private Produto salvarProdutoNoBanco(long sku) {
        Produto produto = new Produto(sku,"produto salvo no banco");
        Inventory inventory = new Inventory(getWarehouses());
        produto.setInventory(inventory);
        mongoTemplate.save(produto);
        return produto;
    }

    private List<Warehouse> getWarehouses() {
        List<Warehouse> warehouses = new ArrayList<Warehouse>();
        warehouses.add(new Warehouse("SP",12, WarehouseType.ECOMMERCE));
        warehouses.add(new Warehouse("MOEMA",3, WarehouseType.PHYSICAL_STORE));
        return warehouses;
    }
}
