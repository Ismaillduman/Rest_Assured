package com.cydeo.day10;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class P04_JsonSchemaValidation extends SpartanTestBase {
    @Test
    void test(){

        given()
                .accept(ContentType.JSON)
                .pathParam("id",20)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));
    }

    @DisplayName("with class name")
    @Test
    void test2(){

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SearchSpartanSchema.json"));
    }
@DisplayName("with filePath")
    @Test
    void test3(){

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/SearchSpartanSchema.json")));
    }
    @DisplayName("jsonschema with all spartans")
    @Test
    void test4(){

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/allSpartanJsonSchema.json")));
    }

    @DisplayName("POST SINGLE SPARTANS")
    @Test
    void test5(){

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/allSpartanJsonSchema.json")));
    }
    /**
     *     Do schema validation for ALLSPARTANS and POST SINGLE SPARTAN
     *
     *     ALL SPARTANS
     *      1- Get all spartans by using /api/spartans
     *      2- Validate schema by using  JsonSchemaValidator
     *
     *
     *    POST SINGLE SPARTANS
     *       1- Post single spartan
     *       2- Validate schema by using  JsonSchemaValidator
     *
     */

}
