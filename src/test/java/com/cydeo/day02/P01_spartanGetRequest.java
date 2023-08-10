package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_spartanGetRequest {
    String url = "http://44.204.20.3:8000";

    @Test
    public void spartanGetRequest() {
        /*
         * Given content type is application/json
         * When user sends GET request /api/spartans endpoint
         * Then status code should be 200
         * And Content type should be application/json
         */

        Response response = RestAssured.given().
                accept(ContentType.JSON). //ContentType.JSON-->application.JSON
                        when().get(url + "/api/spartans");

        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(200, actualStatusCode);

        //how to get respponse content type header?
        String actualContentType = response.contentType();
        System.out.println("actualContentType = " + actualContentType);

        //assert the content type

        Assertions.assertEquals("application/json", actualContentType);

        //how to get Connection header value?
        //if we want to get any response header value, we can use header("headerName")
        //method from response object. it will return header value as string
        System.out.println(response.header("Connection"));
        System.out.println(response.header("Content-type"));
        System.out.println(response.header("Date"));
        //how to verify header exist?
        //hasHeaderWithName method help us to verify header exists or not
        //it is useful for dynamic header values like Date, we are only verifiying header exist or not,not checking the value

        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);

    }

    @Test
    public void getSpartan() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url + "/api/spartans/3");

        //verify status code
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200, actualStatusCode);
        //verify content type is json
        String actualContentType = response.contentType();
        System.out.println("actualContentType = " + actualContentType);
        Assertions.assertEquals("application/json", actualContentType);
        Assertions.assertEquals(response.header("Content-type"), actualContentType);
        //verify body contains "Fidole"
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

        response.prettyPrint();

           /*
            This is not a good way to make assertion. In this way we are just converting response to String and
            with the help of String contains we are just looking into Response.But we should be able to get json
            "name" key value then verify that one is equal to "Fidole"
         */

    }

    /*
   Given no headers provided
   When Users send GET request to /api/hello
   Then response status code should be 200
   And Content type header should be "text/plain;charset=UTF-8"
   And header should contain Date
   And Content-Length should be 17
   And body should be "Hello from Sparta"
    */
    @Test
    public void helloSpartan() {
        Response response = RestAssured.when().get(url + "/api/hello");
        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());

        //verify status code
        Assertions.assertEquals(200, response.statusCode());

        //And Content type header should be "text/plain;charset=UTF-8"

        String actualContentType = response.contentType();

        Assertions.assertEquals("text/plain;charset=UTF-8", actualContentType);

        //And header should contain Date

        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);

        // And Content-Length should be 17

        String headerContentLength = response.header("Content-Length");
        System.out.println("headerContentLength = " + headerContentLength);
        Assertions.assertEquals("17", headerContentLength);

        //And body should be "Hello from Sparta"

        String responseBody = response.body().asString();
        System.out.println("responseBody = " + responseBody);
        Assertions.assertEquals("Hello from Sparta", responseBody);

    }

}
