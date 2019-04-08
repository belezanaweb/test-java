package br.com.blz.testjava.gateways.http;

import static br.com.blz.testjava.usecases.impl.ProductCreateImpl.INTERNAL_SERVER_ERROR;
import static br.com.blz.testjava.usecases.impl.ProductCreateImpl.MSG_SKUS_IGUAIS;
import static br.com.blz.testjava.usecases.impl.ProductDeleteImpl.DELETE_OK;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.blz.testjava.config.advice.ApplicationAdvice;
import br.com.blz.testjava.domains.Inventory;
import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.exceptions.ConflictException;
import br.com.blz.testjava.exceptions.InternalServerErrorException;
import br.com.blz.testjava.gateways.http.converter.ProductConverter;
import br.com.blz.testjava.gateways.http.converter.ProductResponseJSON;
import br.com.blz.testjava.usecases.ProductCreate;
import br.com.blz.testjava.usecases.ProductDelete;
import br.com.blz.testjava.usecases.ProductGet;
import br.com.blz.testjava.usecases.ProductUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ProductController.class, ApplicationAdvice.class})
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductCreate usecaseCreate;

    @MockBean
    private ProductUpdate usecaseUpdate;

    @MockBean
    private ProductGet usecaseGet;

    @MockBean
    private ProductDelete usecaseDelete;

    @MockBean
    private ProductConverter converter;

    @Test
    public void createWillReturnTrue() throws Exception {
        //given
        final Inventory inventory = getInventory();
        final Product domain = getProduct(inventory);
        final ProductResponseJSON responseJSON = getProductResponseJSON(inventory);
        given(this.usecaseCreate.execute(Mockito.any()))
            .willReturn(responseJSON);

        given(this.converter.convertRequestToDomain(Mockito.any()))
            .willReturn(domain);
        //when
        final ResultActions result = this.mockMvc
            .perform(
                post("/api/product")
                    .content(objectMapper.writeValueAsString(domain))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        //then
        result.andExpect(status().isCreated());
    }

    @Test
    public void createWillReturnConflictException() throws Exception {
        //given
        final Inventory inventory = getInventory();
        final Product domain = getProduct(inventory);
        given(this.usecaseCreate.execute(Mockito.any()))
            .willThrow(new ConflictException(MSG_SKUS_IGUAIS));

        given(this.converter.convertRequestToDomain(Mockito.any()))
            .willReturn(domain);
        //when
        final ResultActions result = this.mockMvc
            .perform(
                post("/api/product")
                    .content(objectMapper.writeValueAsString(domain))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        //then
        result
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.error", is(MSG_SKUS_IGUAIS)));
    }

    @Test
    public void createWillReturnInternalServerError() throws Exception {
        //given
        final Inventory inventory = getInventory();
        final Product domain = getProduct(inventory);
        given(this.usecaseCreate.execute(Mockito.any()))
            .willThrow(new InternalServerErrorException(INTERNAL_SERVER_ERROR));

        given(this.converter.convertRequestToDomain(Mockito.any()))
            .willReturn(domain);
        //when
        final ResultActions result = this.mockMvc
            .perform(
                post("/api/product")
                    .content(objectMapper.writeValueAsString(domain))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        //then
        result
            .andExpect(status().is5xxServerError())
            .andExpect(jsonPath("$.error", is(INTERNAL_SERVER_ERROR)));
    }

    @Test
    public void updateWillReturnTrue() throws Exception {
        //given
        final Inventory inventory = getInventory();
        final Product domain = getProduct(inventory);
        final ProductResponseJSON responseJSON = getProductResponseJSON(inventory);

        given(this.usecaseUpdate.execute(Mockito.anyInt(), Mockito.any()))
            .willReturn(responseJSON);

        given(this.converter.convertRequestToDomain(Mockito.any()))
            .willReturn(domain);

        //when
        final ResultActions result = this.mockMvc
            .perform(
                put("/api/product/1234")
                    .content(objectMapper.writeValueAsString(domain))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        //then
        result.andExpect(status().isNoContent());
    }

    @Test
    public void updateWillReturnInternalServerError() throws Exception {
        //given
        final Inventory inventory = getInventory();
        final Product domain = getProduct(inventory);
        given(this.usecaseUpdate.execute(Mockito.anyInt(), Mockito.any()))
            .willThrow(new InternalServerErrorException(INTERNAL_SERVER_ERROR));

        given(this.converter.convertRequestToDomain(Mockito.any()))
            .willReturn(domain);
        //when
        final ResultActions result = this.mockMvc
            .perform(
                put("/api/product/1234")
                    .content(objectMapper.writeValueAsString(domain))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        //then
        result
            .andExpect(status().is5xxServerError())
            .andExpect(jsonPath("$.error", is(INTERNAL_SERVER_ERROR)));
    }

    @Test
    public void getWillReturnTrue() throws Exception {
        //given
        final Inventory inventory = getInventory();
        final Product domain = getProduct(inventory);
        final ProductResponseJSON responseJSON = getProductResponseJSON(inventory);
        given(this.usecaseGet.execute(Mockito.anyInt()))
            .willReturn(responseJSON);

        given(this.converter.convertRequestToDomain(Mockito.any()))
            .willReturn(domain);
        //when
        final ResultActions result = this.mockMvc
            .perform(
                get("/api/product/1234")
                    .content(objectMapper.writeValueAsString(domain))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        //then
        result.andExpect(status().isOk());
    }

    @Test
    public void getWillReturnInternalServerError() throws Exception {
        //given
        final Inventory inventory = getInventory();
        final Product domain = getProduct(inventory);
        given(this.usecaseGet.execute(Mockito.anyInt()))
            .willThrow(new InternalServerErrorException(INTERNAL_SERVER_ERROR));

        given(this.converter.convertRequestToDomain(Mockito.any()))
            .willReturn(domain);
        //when
        final ResultActions result = this.mockMvc
            .perform(
                get("/api/product/1234")
                    .content(objectMapper.writeValueAsString(domain))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        //then
        result
            .andExpect(status().is5xxServerError())
            .andExpect(jsonPath("$.error", is(INTERNAL_SERVER_ERROR)));
    }

    @Test
    public void deleteWillReturnTrue() throws Exception {
        //given
        final Inventory inventory = getInventory();
        final Product domain = getProduct(inventory);
        given(this.usecaseDelete.execute(Mockito.anyInt()))
            .willReturn(DELETE_OK);

        given(this.converter.convertRequestToDomain(Mockito.any()))
            .willReturn(domain);
        //when
        final ResultActions result = this.mockMvc
            .perform(
                delete("/api/product/1234")
                    .content(objectMapper.writeValueAsString(domain))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        //then
        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteWillReturnInternalServerError() throws Exception {
        //given
        final Inventory inventory = getInventory();
        final Product domain = getProduct(inventory);
        given(this.usecaseDelete.execute(Mockito.anyInt()))
            .willThrow(new InternalServerErrorException(INTERNAL_SERVER_ERROR));

        given(this.converter.convertRequestToDomain(Mockito.any()))
            .willReturn(domain);
        //when
        final ResultActions result = this.mockMvc
            .perform(
                delete("/api/product/1234")
                    .content(objectMapper.writeValueAsString(domain))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        //then
        result
            .andExpect(status().is5xxServerError())
            .andExpect(jsonPath("$.error", is(INTERNAL_SERVER_ERROR)));
    }

    private ProductResponseJSON getProductResponseJSON(Inventory inventory) {
        return ProductResponseJSON
            .builder()
            .sku(1234)
            .name("Teste1")
            .inventory(inventory)
            .build();
    }

    private Inventory getInventory() {
        return Inventory
            .builder()
            .quantity(1)
            .warehouses(new ArrayList<>())
            .build();
    }

    private Product getProduct(Inventory inventory) {
        return Product
            .builder()
            .sku(1234)
            .name("Teste1")
            .inventory(inventory)
            .build();
    }
}
