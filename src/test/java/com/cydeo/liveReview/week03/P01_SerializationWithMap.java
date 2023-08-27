package com.cydeo.liveReview.week03;

import com.cydeo.utilities.FruitTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class P01_SerializationWithMap extends FruitTestBase {

    static int createdFruitId;

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
        String self_link=response.path("self_link");
        String id=self_link.substring(self_link.lastIndexOf("/")+1);
        System.out.println("id = " + id);

        createdFruitId= Integer.parseInt(id);
        System.out.println("fruitId = " + createdFruitId);
        System.out.println("response.jsonPath().get(\"id\") = " + response.jsonPath().get("id"));


    }

    @Test
    void getFruit(){

        given().log().uri().
                accept(ContentType.JSON)
                .pathParam("id", createdFruitId)
                .get("/products/{id}")
                .prettyPeek();
    }

}
