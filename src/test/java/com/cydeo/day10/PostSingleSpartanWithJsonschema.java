package com.cydeo.day10;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
public class PostSingleSpartanWithJsonschema extends SpartanTestBase {

    static int id;
    @Test
    void test(){
        String bodyRequest = "{\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"John Doe\",\n" +
                "     \"phone\":8877445596\n" +
                "     }";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(bodyRequest)
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SpartanPostSchema.json"))

                .extract().response();

         id = response.jsonPath().getInt("data.id");
        System.out.println("id = " + id);


    }

    @Test
    void test1(){
        System.out.println("id = " + id);
        given()
                .accept(ContentType.JSON)
                .pathParam("id",id)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));
    }
}
