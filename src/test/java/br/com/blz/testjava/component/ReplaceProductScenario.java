package br.com.blz.testjava.component;

import br.com.blz.testjava.domain.api.request.CreateProductRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.Optional;

import static br.com.blz.testjava.controller.BaseController.PRODUCT_RESOURCE;
import static br.com.blz.testjava.controller.BaseController.SKU_PATH_VARIABLE;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReplaceProductScenario extends BaseScenario {

    @Test
    public void replaceProduct_ok_productReplaced() {
        when(repository.findBySku(anyLong())).thenReturn(Optional.of(mock(CreateProductRequest.class)));

        RestAssured.given().contentType(ContentType.JSON)
            .body(getBodyFromFile(REPLACE_JSON))
            .put(getPath())
            .then()
            .statusCode(200)
            .body(SKU_KEY, equalTo(43264))
            .body(INVENTORYQUANTITY_KEY, equalTo(15))
            .body(MARKETABLE_KEY, equalTo(true));
    }

    @Test
    public void replaceProduct_created_productCreated() {
        when(repository.findBySku(anyLong())).thenReturn(Optional.empty());

        RestAssured.given().contentType(ContentType.JSON)
            .body(getBodyFromFile(REPLACE_JSON))
            .put(getPath())
            .then()
            .statusCode(201)
            .body(SKU_KEY, equalTo(43264))
            .body(INVENTORYQUANTITY_KEY, equalTo(15))
            .body(MARKETABLE_KEY, equalTo(true));
    }

    private String getPath() {
        return HOST + port + PRODUCT_RESOURCE.replace(SKU_PATH_VARIABLE, "43264");
    }
}
