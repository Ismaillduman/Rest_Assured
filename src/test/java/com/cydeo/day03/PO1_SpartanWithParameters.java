package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class PO1_SpartanWithParameters extends SpartanTestBase {

    @DisplayName("GET spartan /api/spartans/{id} path param with 24")
    @Test
    public void test1() {
    /*   Given accept type is Json
        And Id parameter value is 24
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 200
        And response content-type: application/json
        And "Julio" should be in response payload(body)
     */

        Response response = given().accept(ContentType.JSON).when().get("/api/spartans/24");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        boolean isJulio = response.body().asString().contains("Julio");
        assertTrue(isJulio);
        response.prettyPrint();
    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 24)
                .when().get("/api/spartans/{id}");
        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        boolean isJulio = response.body().asString().contains("Julio");
        assertTrue(isJulio);


    }

     /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
      */
@DisplayName("GET Spartan /api/spartans/{id} with invalid ID")
    @Test
    public void test3(){
    Response response = given().accept(ContentType.JSON)
            .and().pathParam("id", 500)
            .when().get("/api/spartans/{id}");

    response.prettyPrint();
    assertEquals(404,response.statusCode());
    assertEquals("application/json",response.contentType());
    assertTrue(response.body().asString().contains("Not Found"));
}

     /*
        Given Accept type is Json
        And query parameter values are:
            gender|Female
            nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
    @DisplayName("GET Request to /api/spartans/search with Query params Map")
    @Test
    public void test4(){
        Map<String,String> queryMap= new HashMap<>();
        queryMap.put("gender", "Female");
        queryMap.put("nameContains", "e");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
        response.prettyPrint();
    }
    @DisplayName("GET Request to /api/spartans/search with Query params")
    @Test
    public void test5(){

    }
}
