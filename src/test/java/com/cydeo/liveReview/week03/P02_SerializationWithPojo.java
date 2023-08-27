package com.cydeo.liveReview.week03;

import com.cydeo.pojo.Fruit;
import com.cydeo.utilities.FruitTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P02_SerializationWithPojo extends FruitTestBase {
    static int createdFruitId;

    @DisplayName("create Fruit With Pojo")
    @Test
    void test1() {

        Fruit requestBody = new Fruit("kiraz", 9.99);

        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .post("/products")
                .then()
                .statusCode(201)
                .extract().response();

        response.prettyPeek();

        System.out.println("response.jsonPath().getObject(\"\", Fruit.class).getName() " +
                "= " + response.jsonPath().getObject("", Fruit.class).getName());


    }

}
