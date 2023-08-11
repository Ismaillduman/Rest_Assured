package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;


public class PO2_NegativeSpartanTest {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://44.204.20.3:8000";
    }
    /*
     * Given accept content type is application/json
     * When user sends get request /api/spartans end point
     *then status code should be 200*/

    @Test
    public void getAllSpartans() {

        Response response = given().accept(ContentType.JSON).when().get("/api/spartans");
        assertEquals(200, response.statusCode());

    }

    @DisplayName("Get- All Spartans -accept,application/xml -406")
    @Test
    public void task2() {
        Response response = given().accept(ContentType.XML).when().get("/api/spartans/10");
        assertEquals(406, response.statusCode());

        response.contentType();
        assertEquals("application/xml;charset=UTF-8", response.contentType());
    }
}
