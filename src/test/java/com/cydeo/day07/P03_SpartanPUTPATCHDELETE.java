package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class P03_SpartanPUTPATCHDELETE extends SpartanTestBase {
    @DisplayName("PUT Spartan with Map")
    @Test
    public void test1(){

        Map<String,Object> requestBodyMap= new LinkedHashMap<>();
        requestBodyMap.put("name","Harry Granger");
        requestBodyMap.put("gender","Male");
        requestBodyMap.put("phone","8877445596");
        int id=115;

        given() .contentType(ContentType.JSON)
                .pathParam("id",id)

                .body(requestBodyMap)
                .when().put("/api/spartans/{id}")
                .then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @DisplayName("PATCH Spartan with Map")
    @Test
    public void test2(){

        Map<String,Object> requestBodyMap= new LinkedHashMap<>();
       // requestBodyMap.put("name","Harry Granger");
        //requestBodyMap.put("gender","Male");
        requestBodyMap.put("phone","8877445866");
        int id=115;

        given() .contentType(ContentType.JSON)
                .pathParam("id",id)

                .body(requestBodyMap)
                .when().patch("/api/spartans/{id}")
                .then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @DisplayName("DELETE Spartan with Map")
    @Test
    public void test3(){

        Map<String,Object> requestBodyMap= new LinkedHashMap<>();
        requestBodyMap.put("name","Harry Granger");
        requestBodyMap.put("gender","Male");
        requestBodyMap.put("phone","8877445866");
        int id=115;

        given()
                .pathParam("id",id)

                .body(requestBodyMap)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(HttpStatus.SC_NO_CONTENT);

        given().pathParam("id",id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
