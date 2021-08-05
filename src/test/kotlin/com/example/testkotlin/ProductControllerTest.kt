package com.example.testkotlin


import org.junit.jupiter.api.AfterAll
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
class ProductControllerTest {

    @Autowired lateinit var mockMVC: MockMvc

    @Test
    @Order(1)
    fun `get all products empty return`(){
        mockMVC.perform(MockMvcRequestBuilders.get("http://localhost:12378/products"))
                .andExpect { MockMvcResultMatchers.status().isNoContent }
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Order(2)
    fun `save new product sku 77`(){
        mockMVC.perform(MockMvcRequestBuilders.post("http://localhost:12378/products/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sku\": 77,\"name\": \"teetwerewrwe\",\"inventory\": {\"warehouses\": [{ \"locality\": \"SP\", \"quantity\": 12, \"type\": \"ECOMMERCE\" },{ \"locality\": \"MOEMA\",\"quantity\": 3, \"type\": \"PHYSICAL_STORE\"}] } }"))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Order(3)
    fun `update product sku 77`(){
        mockMVC.perform(MockMvcRequestBuilders.put("http://localhost:12378/products/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sku\": 77,\"name\": \"teetwerewrwe\",\"inventory\": {\"warehouses\": [{ \"locality\": \"SP\", \"quantity\": 12, \"type\": \"ECOMMERCE\" },{ \"locality\": \"MOEMA\",\"quantity\": 3, \"type\": \"PHYSICAL_STORE\"}] } }"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Order(4)
    fun `get by id`(){
        mockMVC.perform(MockMvcRequestBuilders.get("http://localhost:12378/products/77"))
                .andExpect { MockMvcResultMatchers.status().isOk }
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Order(5)
    fun `get all products`(){
        mockMVC.perform(MockMvcRequestBuilders.get("http://localhost:12378/products"))
                .andExpect { MockMvcResultMatchers.status().isOk }
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Order(6)
    fun `product already exist sku 77`(){
        mockMVC.perform(MockMvcRequestBuilders.post("http://localhost:12378/products/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sku\": 77,\"name\": \"teetwerewrwe\",\"inventory\": {\"warehouses\": [{ \"locality\": \"SP\", \"quantity\": 12, \"type\": \"ECOMMERCE\" },{ \"locality\": \"MOEMA\",\"quantity\": 3, \"type\": \"PHYSICAL_STORE\"}] } }"))
                .andExpect(MockMvcResultMatchers.status().isConflict)
                .andDo(MockMvcResultHandlers.print())
    }

}