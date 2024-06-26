package com.api.Testcases;

import com.api.Basetest.BaseTest;
import com.api.Endpoints.Routes;
import com.api.Utilities.GlobalVariables;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetACartTest extends BaseTest {
    ValidatableResponse validatableResponse;
    Response response;

    @BeforeClass
    public void setUpBeforeCreateCartTest() {
        requestSpecification.basePath(Routes.GET_A_CART);
        requestSpecification.pathParam("cartId", GlobalVariables.CART_ID);
        response = given().spec(requestSpecification)
                .get();
        validatableResponse = response.then().log().all();
    }

    @Test(priority = 1)
    public void validateProductResponseStatusCode() {
        validatableResponse.statusCode(200);
    }

    @Test(priority = 2)
    public void validateResponseIsObject() {
        // Assert that the root of the JSON response is an object
        validatableResponse.assertThat().body("$", instanceOf(Object.class));
    }

    @Test(priority = 3)
    public void validateItemsFieldIsPresent() {
        validatableResponse.body("items", notNullValue());
    }

    @Test(priority = 4)
    public void validateCreatedFieldIsPresent() {
        validatableResponse.body("created", notNullValue());
    }

    @Test(priority = 5)
    public void validateCreatedFieldTypeIsString() {
        validatableResponse.body("created", isA(String.class));
    }

    @Test(priority = 6)
    public void validateItemsFieldTypeIsArray() {
        validatableResponse.body("items", instanceOf(List.class));
    }
}
