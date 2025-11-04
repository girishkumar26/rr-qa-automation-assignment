package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests extends BaseTest {

    @Test(description = "Verify /popular endpoint returns valid response")
    public void verifyHomeEndpoint() {
        Response response = RestAssured
                .given()
                .baseUri("https://tmdb-discover.surge.sh")
                .when()
                .get("/")
                .then()
                .extract().response();

        int statusCode = response.statusCode();
        String contentType = response.getHeader("Content-Type");

        System.out.println("Status Code: " + statusCode);
        System.out.println("Content-Type: " + contentType);

        Assert.assertEquals(statusCode, 200, "Expected status code 200");
        Assert.assertTrue(contentType.contains("text/html") || contentType.contains("application/json"),
                "Expected valid content type");
    }
}