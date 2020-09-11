package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC12_Disconnect
{
    @Test
    public void TC12_Disconnect()
    {
        RestAssured.baseURI = "http://localhost:8080";
        // Request Object
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-type", "application/json");

        //Response object
        Response response = httpRequest.request(Method.POST, "/disconnect");

        //Print response in console window
        String responseBody = response.getBody().asString();
        System.out.println("Response : " + responseBody);

        // Status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is : " + statusCode);
        Assert.assertEquals(statusCode, 200);

        // Success code validation
        boolean successCode = response.jsonPath().get("success");
        Assert.assertEquals(successCode, true);

    }
}
