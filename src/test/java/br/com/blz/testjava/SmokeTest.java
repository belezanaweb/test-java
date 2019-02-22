package br.com.blz.testjava;

import br.com.blz.testjava.controllers.ProductController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SmokeTest {

    @Autowired
    ProductController controller;

    @Autowired
    private MockMvc mvc;

    private static final String BASE_HEALF = "/health";

    @Test
    public void contexLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(BASE_HEALF))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("{\"status\":\"UP\"}")));
    }

}
