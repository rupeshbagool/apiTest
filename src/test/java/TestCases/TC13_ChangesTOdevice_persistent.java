package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestCases.*;

public class TC13_ChangesTOdevice_persistent extends TC06_SetBrightness
{
    @Test
    public void TC13_ChangesTOdevice_persistent()
    {
        try {
            TC06_setbrightness();

            //TC12_Disconnect();

            TC02_connectDeviceBulb();

            //GetState
            RestAssured.baseURI = "http://localhost:8080";
            // Request Object
            RequestSpecification httpRequest = RestAssured.given();
            // Response object
            Response response = httpRequest.request(Method.GET, "/state");
            String responseBody = response.getBody().asString();
            System.out.println("Response : " + responseBody);

            //int brightness = response.jsonPath().get("success");
            float successCode = response.jsonPath().get("brightness");

            Assert.assertEquals(successCode, TC06_setbrightness());


        } catch (AssertionError e)
        {
            System.out.println("Test Case Fail: " + e);
        }
    }
}