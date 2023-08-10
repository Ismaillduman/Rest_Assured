package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_simpleGetRequest {
    String url = "http://44.204.20.3:8000/api/spartans";

    @Test
    public void simpleGetRequest() {

        Response response = RestAssured.get(url);
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200, actualStatusCode);

        response.prettyPrint();
    }
}
