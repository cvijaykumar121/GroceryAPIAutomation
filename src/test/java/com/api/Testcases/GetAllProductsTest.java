package com.api.Testcases;

import com.api.Basetest.BaseTest;
import com.api.Endpoints.Routes;
import com.api.Payloads.Product;
import com.api.Utilities.GlobalVariables;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetAllProductsTest extends BaseTest {
    ValidatableResponse validatableResponse;
    Response response;
    private static List<Product> products;

    @BeforeClass
    public void setUpBeforeGetAllProductsTest() {
        requestSpecification.basePath(Routes.GET_ALL_PRODUCTS);
        response = given().spec(requestSpecification)
                .get();
        validatableResponse = response.then().log().all();
        products = response.jsonPath().getList("", Product.class);
    }

    @Test(priority = 1)
    public void validateProductApiResponseIsAnArray() {
        response.then().assertThat().body("$", instanceOf(List.class));
    }

    @Test(priority = 2)
    public void validate_All_Items_In_Response_Array_Is_An_Object() {
        response.then().body("$", everyItem(instanceOf(Object.class)));
    }

    @Test(priority = 3)
    public void validateFieldsInEachProduct() {
        response.then().assertThat()
                .body("id", everyItem(notNullValue()))
                .body("category", everyItem(notNullValue()))
                .body("name", everyItem(notNullValue()))
                .body("inStock", everyItem(notNullValue()));
    }

    @Test(priority = 4)
    public void validateProductNameIsNonEmptyString() {
        response.then().assertThat()
                .body("name", everyItem(not(emptyString())));
    }

    @Test(priority = 5)
    public void validateDataTypesOfFields() {
        response.then().assertThat()
                .body("id", everyItem(isA(Integer.class)))
                .body("category", everyItem(isA(String.class)))
                .body("name", everyItem(isA(String.class)))
                .body("inStock", everyItem(isA(Boolean.class)));
    }

//    @Test(priority = 6)
//    public void validateNoDuplicateIds() {
//        // Collect all ids in a Set to check for duplicates
//        Set<Integer> ids = new HashSet<>();
//        for (Product product : products) {
//            assertThat("Duplicate id found: " + product.getId(), ids.add(product.getId()), is(true));
//        }
//    }

    @Test(priority = 7)
    public void validateCoffeeCategoryPresence() {
        boolean coffeeCategoryPresent = products.stream()
                .anyMatch(product -> product.getCategory().equals("coffee"));
        assertThat("No product found in 'coffee' category", coffeeCategoryPresent, is(true));
    }

    @Test(priority = 8)
    public void validateFreshProduceCategoryPresence() {
        boolean freshProduceCategoryPresent = products.stream()
                .anyMatch(product -> product.getCategory().equals("fresh-produce"));
        assertThat("No product found in 'fresh-produce' category", freshProduceCategoryPresent, is(true));
    }

    @Test(priority = 9)
    public void assignIdsOfProductsInStock() {
        for (Product product : products) {
            if (product.isInStock()) {
                GlobalVariables.PRODUCT_ID = product.getId();
                break;
            }
        }
    }
}
