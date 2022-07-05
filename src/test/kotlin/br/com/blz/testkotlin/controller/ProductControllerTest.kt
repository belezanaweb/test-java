package br.com.blz.testkotlin.controller

import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
internal class ProductControllerTest {

  @Autowired
  lateinit var mockMVC: MockMvc

  @Test
  @Order(1)
  fun getAllProductsEmpty() {
    mockMVC.perform(MockMvcRequestBuilders.get("http://localhost:8080/v1/product/all"))
      .andExpect { MockMvcResultMatchers.status().isNoContent }
      .andDo(MockMvcResultHandlers.print())
  }

  @Test
  @Order(2)
  fun createProduct43267() {
    mockMVC.perform(
      MockMvcRequestBuilders.post("http://localhost:8080/v1/product")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"sku\": 43267,\"name\": \"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g\",\"inventory\": {\"warehouses\": [{ \"locality\": \"SP\", \"quantity\": 12, \"type\": \"ECOMMERCE\" },{ \"locality\": \"MOEMA\",\"quantity\": 3, \"type\": \"PHYSICAL_STORE\"}] } }")
    )
      .andExpect(MockMvcResultMatchers.status().isCreated)
      .andDo(MockMvcResultHandlers.print())
  }

  @Test
  @Order(3)
  fun editProduct43267() {
    mockMVC.perform(
      MockMvcRequestBuilders.put("http://localhost:8080/v1/product/43267")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"sku\": 43267,\"name\": \"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g\",\"inventory\": {\"warehouses\": [{ \"locality\": \"SP\", \"quantity\": 0, \"type\": \"ECOMMERCE\" },{ \"locality\": \"MOEMA\",\"quantity\": 3, \"type\": \"PHYSICAL_STORE\"}] } }")
    )
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andDo(MockMvcResultHandlers.print())
  }

  @Test
  @Order(4)
  fun getBySku43267() {
    mockMVC.perform(MockMvcRequestBuilders.get("http://localhost:8080/v1/product/43267"))
      .andExpect { MockMvcResultMatchers.status().isOk }
      .andDo(MockMvcResultHandlers.print())
  }

  @Test
  @Order(5)
  fun getAllProducts() {
    mockMVC.perform(MockMvcRequestBuilders.get("http://localhost:8080/v1/product/all"))
      .andExpect { MockMvcResultMatchers.status().isOk }
      .andDo(MockMvcResultHandlers.print())
  }

  @Test
  @Order(6)
  fun createProduct43267Exception() {
    mockMVC.perform(
      MockMvcRequestBuilders.post("http://localhost:8080/v1/product")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"sku\": 43267,\"name\": \"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g\",\"inventory\": {\"warehouses\": [{ \"locality\": \"SP\", \"quantity\": 12, \"type\": \"ECOMMERCE\" },{ \"locality\": \"MOEMA\",\"quantity\": 3, \"type\": \"PHYSICAL_STORE\"}] } }")
    )
      .andExpect(MockMvcResultMatchers.status().isConflict)
      .andDo(MockMvcResultHandlers.print())
  }

}
