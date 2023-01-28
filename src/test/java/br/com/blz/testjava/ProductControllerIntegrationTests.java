package br.com.blz.testjava;

import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.dto.WarehouseDto;
import br.com.blz.testjava.helper.ProductConstants;
import br.com.blz.testjava.repository.dao.ProductRepository;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTests {

    @SpyBean
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Gson gson;

    @Before
    public void setup() {
        productRepository.removeAll();
    }

    @Test
    public void shouldCreate() throws Exception {
        String json = gson.toJson(ProductConstants.getLoreal());

        mvc.perform(post("/api/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
        ).andExpect(status().isOk());
    }


    @Test
    public void shouldThrowExceptionWhenTryCreateProductAgain() throws Exception {
        shouldCreate();

        String json = gson.toJson(ProductConstants.getLoreal());

        mvc.perform(post("/api/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
        ).andExpect(status().is(400));
    }

    @Test
    public void shouldUpdate() throws Exception {
        shouldCreate();

        ProductDto product = ProductConstants.getLoreal();
        final long SKU = product.getSku();

        WarehouseDto warehouseSP = product.getInventory().getWarehouses().get(0);
        warehouseSP.setQuantity(1);

        Gson gson = new Gson();
        String json = gson.toJson(product);

        mvc.perform(put("/api/"+SKU)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldReturn404WhenTryUpdateProductNotExists() throws Exception {
        ProductDto product = ProductConstants.getLoreal();
        final long SKU = product.getSku();

        WarehouseDto warehouseSP = product.getInventory().getWarehouses().get(0);
        warehouseSP.setQuantity(1);

        Gson gson = new Gson();
        String json = gson.toJson(product);

        mvc.perform(put("/api/"+SKU)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            )
            .andExpect(status().is(404));
    }

    @Test
    public void shouldDelete() throws Exception {
        shouldCreate();

        ProductDto product = ProductConstants.getLoreal();
        final long SKU = product.getSku();

        mvc.perform(delete("/api/product/"+SKU)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldReturn404WhenTryDeleteProductNotExists() throws Exception {
        mvc.perform(delete("/api/product/"+1)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(404));
    }

    @Test
    public void shouldGet() throws Exception {
        shouldCreate();

        ProductDto product = ProductConstants.getLoreal();
        final long SKU = product.getSku();

        mvc.perform(get("/api/product/"+SKU)
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sku", is((int)SKU)))
            .andExpect(jsonPath("$.name", is(product.getName())))
            .andExpect(jsonPath("$.isMarketable", is(true)))
            .andExpect(jsonPath("$.inventory.quantity", is(15)));
    }


    @Test
    public void shouldGetAll() throws Exception {
        shouldCreate();

        ProductDto product = ProductConstants.getLoreal();
        final long SKU = product.getSku();

        mvc.perform(get("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].sku", is((int)SKU)))
            .andExpect(jsonPath("$[0].name", is(product.getName())));
    }

}
