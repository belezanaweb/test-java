package br.com.blz.testjava.component;

import br.com.blz.testjava.domain.api.request.CreateProductRequest;
import io.restassured.RestAssured;
import org.junit.Test;

import java.util.Optional;

import static br.com.blz.testjava.controller.BaseController.PRODUCT_RESOURCE;
import static br.com.blz.testjava.controller.BaseController.SKU_PATH_VARIABLE;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteProductScenario extends BaseScenario {

    @Test
    public void deleteProduct_noContent_productDeleted() {
        when(repository.delete(anyLong())).thenReturn(Optional.of(mock(CreateProductRequest.class)));

        RestAssured.delete(getPath())
            .then()
            .statusCode(204);
    }

    @Test
    public void deleteProduct_notFound() {
        when(repository.delete(anyLong())).thenReturn(Optional.empty());

        RestAssured.delete(getPath())
            .then()
            .statusCode(404);
    }

    private String getPath() {
        return HOST + port + PRODUCT_RESOURCE.replace(SKU_PATH_VARIABLE, "43264");
    }
}
