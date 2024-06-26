package com.api.Testcases;

import com.api.Endpoints.Routes;
import com.api.Basetest.BaseTest;
import com.api.Utilities.GlobalVariables;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.IsEqual.equalTo;

public class APIStatusTest extends BaseTest {
    ValidatableResponse validatableResponse;

    @BeforeClass
    public void setUpBeforeAPIStatusTests() {
        requestSpecification.basePath(Routes.API_STATUS);
        Response response = RestAssured.given().spec(requestSpecification)
                .get();
        validatableResponse = response.then().log().all();
    }

    @Test(priority = 1)
    @Description("Verify that the Booking can be Created")
    public void validateStatusCode() {
        validatableResponse.statusCode(200);
    }

    @Test(priority = 2)
    public void validateContentType() {
        validatableResponse.contentType("application/json");
    }

    @Test(priority = 3)
    public void validateStatusKeyIsPresentInBody() {
        validatableResponse.body("$", hasKey("status"));
    }

    @Test(priority = 4)
    public void validateStatusKeyValue() {
        validatableResponse.body("status", equalTo("UP"));
    }

    @Test(priority = 5, dependsOnMethods = { "validateStatusCode", "validateContentType", "validateStatusKeyIsPresentInBody", "validateStatusKeyValue"})
    public void initializeStatusOfAPIToGlobalVariable() {
        GlobalVariables.APIStatus = "UP";
        Assert.assertEquals(GlobalVariables.APIStatus, "UP");
    }
}
