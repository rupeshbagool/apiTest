package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC06_SetBrightness extends TC02_ConnectDevice
{
    double brightnessvalue;


    @Test
        public double TC06_setbrightness()
        {
            this.brightnessvalue=4.0;
            try {
                TC02_connectDeviceBulb();

                double setBrightness = this.brightnessvalue;

                RestAssured.baseURI = "http://localhost:8080";
                // Request Object
                RequestSpecification httpRequest = RestAssured.given();
                // Response Object
                JSONObject requestParams = new JSONObject();
                requestParams.put("brightness", setBrightness);

                httpRequest.header("Content-type", "application/json");
                httpRequest.body(requestParams.toString());

                //Response object
                Response response = httpRequest.request(Method.POST, "/brightness");

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
            return brightnessvalue;

        }
}
