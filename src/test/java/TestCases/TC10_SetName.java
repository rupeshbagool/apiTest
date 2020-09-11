package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC10_SetName extends TC02_ConnectDevice
{
    @Test
    public void TC10_setname ()
    {
        try {
            TC02_connectDeviceBulb();
            RestAssured.baseURI = "http://localhost:8080";
            // Request Object
            RequestSpecification httpRequest = RestAssured.given();
            // Response Object
            JSONObject requestParams = new JSONObject();
            requestParams.put("name", "foobar");

            httpRequest.header("Content-type", "application/json");
            httpRequest.body(requestParams.toString());

            //Response object
            Response response = httpRequest.request(Method.POST, "/name");

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

        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
    }
}
