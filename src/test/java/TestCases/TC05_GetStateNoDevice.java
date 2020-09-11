package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05_GetStateNoDevice extends TC12_Disconnect
{
    @Test
    public void TC05_getStateNoDevice()
    {
        try {
            TC12_Disconnect();
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

            // Success code validation
            boolean successCode = response.jsonPath().get("success");
            Assert.assertEquals(successCode, false);
        }
        catch ( Exception e )
        {
            System.out.println ("Exception"+ e);
        }
    }
}

