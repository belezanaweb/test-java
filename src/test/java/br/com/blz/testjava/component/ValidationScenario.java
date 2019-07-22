package br.com.blz.testjava.component;

import br.com.blz.testjava.domain.api.request.CreateProductRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.Optional;

import static br.com.blz.testjava.controller.BaseController.PRODUCT_RESOURCE;
import static br.com.blz.testjava.controller.BaseController.PRODUCT_ROOT;
import static br.com.blz.testjava.controller.BaseController.SKU_PATH_VARIABLE;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidationScenario extends BaseScenario {

    @Test
    public void validateOnCreateProduct() {
        RestAssured.given().contentType(ContentType.JSON)
            .body(getBodyFromFile(ERROR_JSON))
            .post(getCreatePath())
            .then()
            .statusCode(400)
            .body(ERROR_FIELD_KEY, notNullValue())
            .body(ERROR_CAUSE_KEY, notNullValue());
    }

    @Test
    public void validateOnReplaceProduct() {
        when(repository.findBySku(anyLong())).thenReturn(Optional.of(mock(CreateProductRequest.class)));

        RestAssured.given().contentType(ContentType.JSON)
            .body(getBodyFromFile(ERROR_JSON))
            .put(getReplacePath())
            .then()
            .statusCode(400)
            .body(ERROR_FIELD_KEY, notNullValue())
            .body(ERROR_CAUSE_KEY, notNullValue());
    }

    private String getCreatePath() {
        return HOST + port + PRODUCT_ROOT;
    }

    private String getReplacePath() {
        return HOST + port + PRODUCT_RESOURCE.replace(SKU_PATH_VARIABLE, "43264");
    }
}
