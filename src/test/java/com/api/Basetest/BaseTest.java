package com.api.Basetest;

import com.api.actions.AssertActions;
import com.api.Endpoints.Routes;
import com.api.PayloadManagers.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public RequestSpecBuilder requestSpecBuilder;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
//    public Response response;

    @BeforeClass
    public void setUpConfig() {
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(Routes.BASE_URL).addHeader("Content-Type", "application/json");
        requestSpecification = requestSpecBuilder.build().log().all();
    }

//    public String getToken() {
//        requestSpecification = RestAssured.given().baseUri(Routes.BASE_URL).basePath("/auth");
//        String payload = "{\n" +
//                "    \"username\" : \"admin\",\n" +
//                "    \"password\" : \"password123\"\n" +
//                "}";
//        response = requestSpecification.contentType(ContentType.JSON)
//                .body(payload)
//                .when().post();
//        jsonPath = new JsonPath(response.asString());
//        return jsonPath.getString("token");
//
//    }
}
