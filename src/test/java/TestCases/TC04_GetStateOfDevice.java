package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC04_GetStateOfDevice
{
    /*Get state of the Device: GET `/state`*/

    @Test
    public void TC04_StateOfDevices()
    {
        RestAssured.baseURI = "http://localhost:8080";
        // Request Object
        RequestSpecification httpRequest = RestAssured.given();
        // Response object
        Response response = httpRequest.request(Method.GET, "/state");
        String responseBody = response.getBody().asString();
        System.out.println("Response : " + responseBody);
        // Check Status Code
        int StatusCode = response.getStatusCode();
        Assert.assertEquals(StatusCode, 200);
        System.out.println("Response : " + StatusCode);
    }
}
