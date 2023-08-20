package com.cydeo.liveReview.week02;

import com.cydeo.utilities.FruitTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
public class P01_Path_param extends FruitTestBase {
/**
 *1- Given accept type is Json
 *2- Path Parameters value is

 id —> 8
 *3- When user sends GET request to /products/{id}*4- Verify followings
 Status code should be 200
 Content Type is application/json
 Print response
 id is 8
 Name is "Grapes"
 Vendor name is "Max Obsthof GmbH"
 **/
    @Test
    public void test_with_junit(){


   Response response=given().log().uri().
                accept(ContentType.JSON)
                .pathParam("id", 5)
                .get("/products/{id}")
                .prettyPeek();

        Assertions.assertEquals("application/json",response.contentType()); //junit
        Assertions.assertEquals(HttpStatus.SC_OK,response.statusCode());

        Assertions.assertEquals(5, (Integer) response.path("id"));
        Assertions.assertEquals("Dragon-Fruit",  response.path("name"));
        Assertions.assertEquals("Exotics Fruit Lair Ltd.",  response.path("vendors[0].name"));


    }
    @Test
    public void test2_with_jsonPath(){
        JsonPath jsonPath = given().log().uri().
                accept(ContentType.JSON)
                .pathParam("id", 5)
                .get("/products/{id}")
                .prettyPeek().jsonPath();


        assertEquals("Dragon-Fruit",jsonPath.getString("name"));
        assertEquals(5,jsonPath.getInt("id"));
        assertEquals("Exotics Fruit Lair Ltd.",jsonPath.get("vendors[0].name"));
    }

    @Test
    public void test2_with_hamCrest(){
      given().log().uri().
                accept(ContentType.JSON)
                .pathParam("id", 4)
                .get("/products/{id}")
                .prettyPeek().
              then()
                .assertThat().statusCode(HttpStatus.SC_OK) //hamcrest
                .contentType("application/json")
                .body("id",is(4))
              .body("name",is("Coconut"))
              .body("vendors[0].name",is("True Fruits Inc."));




    }
}
