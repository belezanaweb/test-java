package test.blz;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import lombok.var;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class ProductIntegrationTest extends BaseIntegrationTest {

    @Test
    public void testCreateProduct () {

        var response = given()
                .log().all()
                .body(buildCreateRequest(1L))
                .contentType(ContentType.JSON)
                .post(PATH)
                .then()
                .extract();

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.body());
        Assert.assertEquals(HttpStatus.SC_CREATED, response.statusCode());
    }


    @Test
    public void testUpdateProduct () {

        var response = given()
                .log().all()
                .body(buildCreateRequest(1L))
                .contentType(ContentType.JSON)
                .post(PATH)
                .then()
                .extract();

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.body());
        Assert.assertEquals(HttpStatus.SC_CREATED, response.statusCode());

        response = given()
                .log().all()
                .body(buildUpdateRequest())
                .contentType(ContentType.JSON)
                .put(PATH)
                .then()
                .extract();

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.body());
        Assert.assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    public void testCreateProductAlreadyRegistered () {

        var response = given()
                .log().all()
                .body(buildCreateRequest(1L))
                .contentType(ContentType.JSON)
                .post(PATH)
                .then()
                .extract();

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.body());
        Assert.assertEquals(HttpStatus.SC_CREATED, response.statusCode());

        response = given()
                .log().all()
                .body(buildCreateRequest(1L))
                .contentType(ContentType.JSON)
                .post(PATH)
                .then()
                .extract();

        Assert.assertEquals(HttpStatus.SC_FORBIDDEN, response.statusCode());
    }

    @Test
    public void testFindProduct () {

        given()
                .log().all()
                .body(buildCreateRequest(1L))
                .contentType(ContentType.JSON)
                .post(PATH)
                .then()
                .extract();

        var response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .get(PATH + "/1")
                .then()
                .extract();

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.body());
        Assert.assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    public void testFindProductNotRegistered () {

        var response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .get(PATH + "/1")
                .then()
                .extract();

        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
    }

    @Test
    public void testDeleteProductFromCache () {

        var response = given()
                .log().all()
                .body(buildCreateRequest(1L))
                .contentType(ContentType.JSON)
                .post(PATH)
                .then()
                .extract();

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.body());
        Assert.assertEquals(HttpStatus.SC_CREATED, response.statusCode());

        response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .delete(PATH + "/1")
                .then()
                .extract();

        Assert.assertEquals(HttpStatus.SC_NO_CONTENT, response.statusCode());
    }

    @Test
    public void testDeleteAllProductsFromCache () {

        var response = given()
                .log().all()
                .body(buildCreateRequest(1L))
                .contentType(ContentType.JSON)
                .post(PATH)
                .then()
                .extract();

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.body());
        Assert.assertEquals(HttpStatus.SC_CREATED, response.statusCode());

        response = given()
                .log().all()
                .body(buildCreateRequest(2L))
                .contentType(ContentType.JSON)
                .post(PATH)
                .then()
                .extract();

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.body());
        Assert.assertEquals(HttpStatus.SC_CREATED, response.statusCode());

        response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .delete("/blz-api/clear")
                .then()
                .extract();

        Assert.assertEquals(HttpStatus.SC_NO_CONTENT, response.statusCode());
    }
}
