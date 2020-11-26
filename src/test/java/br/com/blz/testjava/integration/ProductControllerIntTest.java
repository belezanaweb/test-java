package br.com.blz.testjava.integration;

import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.entities.WareHouse;
import br.com.blz.testjava.enums.WareHouseTypeEnum;
import br.com.blz.testjava.model.response.ProductResponse;
import br.com.blz.testjava.unit.compose.ProductCompose;
import br.com.blz.testjava.unit.compose.Utils;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerIntTest extends BaseIntTest {

    // path
    private static final String FIND_PRODUCT = "/product-controller/SKU/{sku}";
    private static final String CREATE_PRODUCT = "/product-controller/";
    private static final String UPDATE_PRODUCT = "/product-controller/";
    private static final String DELETE_PRODUCT = "/product-controller/SKU/{sku}";

    // contants
    private static final Gson GSON = new Gson();
    private static final String SKU = "\\{sku\\}";


    private ResultActions criarProduto(Product product) throws Exception {

        String jsonContent = GSON.toJson(product);

        return mockMvc.perform(post(CREATE_PRODUCT)
            .contentType(APPLICATION_JSON)
            .content(jsonContent)
        )
            .andDo(print())
            ;
    }

    @Test
    void deveCriarProdutoComSucesso() throws Exception {

        Product product = ProductCompose.getProductDefault();

        ResultActions resultActions = criarProduto(product);

        String resultAsString = resultActions
            .andExpect(jsonPath("$.sku").exists())
            .andExpect(jsonPath("$.sku").value(product.getSku()))
            .andExpect(jsonPath("$.name").exists())
            .andExpect(jsonPath("$.name").value(product.getName()))
            .andExpect(jsonPath("$.marketable").exists())
            .andExpect(jsonPath("$.marketable").isBoolean())
            .andExpect(jsonPath("$.inventory").exists())
            .andExpect(jsonPath("$.inventory.quantity").exists())
            .andExpect(jsonPath("$.inventory.quantity").value(product.getInventory().getQuantity()))
            .andExpect(jsonPath("$.inventory.warehouses").exists())
            .andExpect(jsonPath("$.inventory.warehouses[0].locality").exists())
            .andExpect(jsonPath("$.inventory.warehouses[0].quantity").exists())
            .andExpect(jsonPath("$.inventory.warehouses[0].type").exists())
            .andExpect(jsonPath("$.inventory.warehouses[*].locality", hasSize(2)))
            .andExpect(jsonPath("$.inventory.warehouses[*].quantity", hasSize(2)))
            .andExpect(jsonPath("$.inventory.warehouses[*].type", hasSize(2)))
            .andReturn()
            .getResponse()
            .getContentAsString();

        ProductResponse response = objectMapper.readValue(resultAsString, ProductResponse.class);
        System.out.println("deveCriarProdutoComSucesso" + " | " + response.toString());
    }

    @Test
    void deveProcurarProdutoInexistente_retornaProdutoVazio() throws Exception {

        String sku = Utils.getPalavraAleatoria();

        String findProduct = FIND_PRODUCT.replaceAll(SKU, sku);
        mockMvc.perform(get(findProduct)
        )
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$").doesNotExist()) //retornado null
        ;

    }

    @Test
    void deveAtualizarProdutoComSucesso() throws Exception {

        Product randomProduct = ProductCompose.getProductRandom();
        criarProduto(randomProduct);

        randomProduct.getInventory().getWarehouses().stream().findAny().get().setLocality("ABC");
        randomProduct.getInventory().getWarehouses().stream().findAny().get().setQuantity(123);
        randomProduct.getInventory().getWarehouses().stream().findAny().get().setType(WareHouseTypeEnum.ECOMMERCE);

        String jsonContent = GSON.toJson(randomProduct);

        String[] localities = randomProduct.getInventory().getWarehouses().stream().map(WareHouse::getLocality).distinct().toArray(String[]::new);
        Integer[] quantities = randomProduct.getInventory().getWarehouses().stream().map(WareHouse::getQuantity).distinct().toArray(Integer[]::new);

        String resultAsString = mockMvc.perform(put(UPDATE_PRODUCT)
            .contentType(APPLICATION_JSON)
            .content(jsonContent)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sku").exists())
            .andExpect(jsonPath("$.sku").value(randomProduct.getSku()))
            .andExpect(jsonPath("$.name").exists())
            .andExpect(jsonPath("$.name").value(randomProduct.getName()))
            .andExpect(jsonPath("$.marketable").exists())
            .andExpect(jsonPath("$.marketable").isBoolean())
            .andExpect(jsonPath("$.inventory").exists())
            .andExpect(jsonPath("$.inventory.quantity").exists())
            .andExpect(jsonPath("$.inventory.quantity").value(randomProduct.getInventory().getQuantity()))
            .andExpect(jsonPath("$.inventory.warehouses").exists())
            .andExpect(jsonPath("$.inventory.warehouses[0].locality").exists())
            .andExpect(jsonPath("$.inventory.warehouses[0].quantity").exists())
            .andExpect(jsonPath("$.inventory.warehouses[0].type").exists())
            .andExpect(jsonPath("$.inventory.warehouses[*].locality", hasSize(10)))
            .andExpect(jsonPath("$.inventory.warehouses[*].locality").value(containsInAnyOrder(localities)))
            .andExpect(jsonPath("$.inventory.warehouses[*].quantity", hasSize(10)))
            .andExpect(jsonPath("$.inventory.warehouses[*].quantity").value(containsInAnyOrder(quantities)))
            .andExpect(jsonPath("$.inventory.warehouses[*].type", hasSize(10)))
            .andReturn()
            .getResponse()
            .getContentAsString();

        ProductResponse response = objectMapper.readValue(resultAsString, ProductResponse.class);
        System.out.println("deveAtualizarProdutoComSucesso" + " | " + response);
    }

    @Test
    void deveRemoverProdutoComSucesso() throws Exception {

        //cria
        Product productRandom = ProductCompose.getProductRandom();
        criarProduto(productRandom);

        //remove
        String jsonContent = GSON.toJson(productRandom);
        String deleteProduct = DELETE_PRODUCT.replaceAll(SKU, productRandom.getSku());
        mockMvc.perform(delete(deleteProduct)
            .contentType(APPLICATION_JSON)
            .content(jsonContent)
        )
            .andExpect(status().isOk())
            .andDo(print());

        //procura p/ garantir que não existe
        String findProduct = FIND_PRODUCT.replaceAll(SKU, productRandom.getSku());
        mockMvc.perform(get(findProduct)
        )
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$").doesNotExist()) //retornado null
        ;
    }

}
