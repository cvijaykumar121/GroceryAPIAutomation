package com.api.Testcases;

import com.api.Basetest.BaseTest;
import com.api.Endpoints.Routes;
import com.api.Utilities.GlobalVariables;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetSingleProductTest extends BaseTest {
    ValidatableResponse validatableResponse;
    Response response;

    @BeforeClass
    public void setUpBeforeGetSingleProductsTest() {
        requestSpecification.basePath(Routes.GET_SINGLE_PRODUCT);
        requestSpecification.pathParam("productId", GlobalVariables.PRODUCT_ID);
        response = given().spec(requestSpecification)
                .get();
        validatableResponse = response.then().log().all();
    }

//    {
//        "id": 4643,
//            "category": "coffee",
//            "name": "Starbucks Coffee Variety Pack, 100% Arabica",
//            "manufacturer": "Starbucks",
//            "price": 40.91,
//            "current-stock": 14,
//            "inStock": true
//    }

    @Test(priority = 1)
    public void validateProductResponseStatusCode() {
        validatableResponse.statusCode(200);
    }

    @Test(priority = 2)
    public void validateProductIdIsPresent() {
        validatableResponse.body("id", notNullValue());
    }

    @Test(priority = 3)
    public void validateProductCategoryIsNotEmpty() {
        validatableResponse.body("category", not(emptyString()));
    }

    @Test(priority = 4)
    public void validateProductNameIsNotEmpty() {
        validatableResponse.body("name", not(emptyString()));
    }

    @Test(priority = 6)
    public void validateProductPriceIsPositive() {
        validatableResponse.body("price", greaterThanOrEqualTo(0.0f)); // Adjust based on your business logic
    }

    @Test(priority = 7)
    public void validateCurrentStockIsNonNegative() {
        validatableResponse.body("current-stock", greaterThanOrEqualTo(0));
    }

    @Test(priority = 9)
    public void validateManufacturerNotNull() {
        validatableResponse.body("manufacturer", notNullValue());
    }

    @Test(priority = 10)
    public void validatePriceNotNull() {
        validatableResponse.body("price", notNullValue());
    }

    @Test(priority = 11)
    public void validateCurrentStockNotNull() {
        validatableResponse.body("current-stock", notNullValue());
    }

    @Test(priority = 12)
    public void validateInStockNotNull() {
        validatableResponse.body("inStock", notNullValue());
    }

    @Test(priority = 13)
    public void validateProductIdType() {
        validatableResponse.body("id", isA(Integer.class));
    }

    @Test(priority = 8)
    public void validateInStockIsBoolean() {
        validatableResponse.body("inStock", isA(Boolean.class));
    }

    @Test(priority = 15)
    public void validateCategoryTypeIsString() {
        validatableResponse.body("category", isA(String.class));
    }

    @Test(priority = 16)
    public void validateNameType() {
        validatableResponse.body("name", isA(String.class));
    }

    @Test(priority = 17)
    public void validateManufacturerType() {
        validatableResponse.body("manufacturer", isA(String.class));
    }

    @Test(priority = 18)
    public void validateCurrentStockType() {
        validatableResponse.body("current-stock", isA(Integer.class));
    }

    @Test(priority = 16)
    public void validateInStockMatchesCurrentStock() {
        // Retrieve values from response
        boolean inStock = response.path("inStock");
//        int currentStock = response.path("current-stock");

        // Validate condition: inStock should be true only if currentStock > 0
        if (inStock) {
            validatableResponse.body("current-stock", greaterThan(0));
        } else {
            validatableResponse.body("current-stock", equalTo(0));
        }
    }
}
