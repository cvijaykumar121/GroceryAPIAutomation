package com.api.Testcases;

import com.api.Basetest.BaseTest;
import com.api.Endpoints.Routes;
import com.api.Utilities.GlobalVariables;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class AddItemToCartTest extends BaseTest {
    ValidatableResponse validatableResponse;
    Response response;

    @BeforeClass
    public void setUpBeforeCreateCartTest() {
        requestSpecification.basePath(Routes.ADD_ITEM_TO_CART);
        requestSpecification.pathParam("cartId", GlobalVariables.CART_ID);

        response = given().spec(requestSpecification)
                .get();
        validatableResponse = response.then().log().all();
    }
}
