package com.cydeo.liveReview.week03;

import com.cydeo.utilities.FruitTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class P01_SerializationWithMap extends FruitTestBase {



    @Test
    void createFruit(){

        Map<String,Object> requestBody= new LinkedHashMap<>();
        requestBody.put("name","apple");
        requestBody.put("price",2.79);
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .post("/products")
                .then()
                .statusCode(201)
                .extract().response();

        response.prettyPeek();


    }

}
