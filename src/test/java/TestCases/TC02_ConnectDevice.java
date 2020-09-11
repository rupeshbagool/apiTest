package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02_ConnectDevice extends TC12_Disconnect
{
    @Test
    public void TC02_connectDeviceBulb()
    {
        try
        {
        TC12_Disconnect();
        RestAssured.baseURI = "http://localhost:8080";
        // Request Object
        RequestSpecification httpRequest = RestAssured.given();
        // Response Object
        JSONObject requestParams = new JSONObject();
        requestParams.put("ip", "192.168.100.10");

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(requestParams.toString());

        //Response object
        Response response = httpRequest.request(Method.POST, "/connect");

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
        catch ( Exception e )
        {
            System.out.println ("Exception"+ e);
        }
    }
}
