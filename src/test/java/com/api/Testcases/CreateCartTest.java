package com.api.Testcases;

import com.api.Basetest.BaseTest;
import com.api.Endpoints.Routes;
import com.api.Utilities.GlobalVariables;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateCartTest extends BaseTest {
    ValidatableResponse validatableResponse;
    Response response;

    @BeforeClass
    public void setUpBeforeCreateCartTest() {
        requestSpecification.basePath(Routes.CREATE_NEW_CART);
        response = given().spec(requestSpecification)
                .post();
        validatableResponse = response.then().log().all();
    }

    @Test(priority = 1)
    public void validateProductResponseStatusCode() {
        validatableResponse.statusCode(201);
    }

    @Test(priority = 2)
    public void validateResponseIsObject() {
        // Assert that the root of the JSON response is an object
        validatableResponse.assertThat().body("$", instanceOf(Object.class));
    }

    @Test(priority = 3)
    public void validateCreatedFieldIsPresent() {
        validatableResponse.body("created", notNullValue());
    }

    @Test(priority = 4)
    public void validateCreatedFieldValueIsNotEmpty() {
        validatableResponse.body("created", not(emptyString()));
    }

    @Test(priority = 5)
    public void validateCartIdFieldIsPresent() {
        validatableResponse.body("cartId", notNullValue());
    }

    @Test(priority = 6)
    public void validateCartIdValueIsNotEmpty() {
        validatableResponse.body("cartId", not(emptyString()));
    }

    @Test(priority = 7)
    public void validateDatatypeOfCreatedField() {
        // Assert that the root of the JSON response is an object
        validatableResponse.assertThat().body("created", instanceOf(Boolean.class));
    }

    @Test(priority = 8)
    public void validateDatatypeOfCartIdField() {
        // Assert that the root of the JSON response is an object
        validatableResponse.assertThat().body("cartId", instanceOf(String.class));
    }

    @Test(priority = 9)
    public void assignIdsOfProductsInStock() {
        GlobalVariables.CART_ID = response.path("cartId");
        Assert.assertEquals(GlobalVariables.CART_ID, response.path("cartId"));
    }
}
