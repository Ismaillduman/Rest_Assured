package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_getSpartanAutBasic extends SpartanAuTestBase {

    @DisplayName("GET Spartan with not Authorization")
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().log().all()
                .statusCode(HttpStatus.SC_UNAUTHORIZED) //Unauthorized 401
                .body("message",is("Unauthorized"));
    }

    @DisplayName("GET Spartan with  Authorization")
    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .auth().basic("user","user")
                .when().get("/api/spartans")
                .then().log().all()
                .statusCode(HttpStatus.SC_OK);
    }
@DisplayName("DELETE /api/spartans/{id} --> EDITOR expect 403 -->Forbiden")
    @Test
    public void test3(){
    given().accept(ContentType.JSON)
            .auth().basic("editor","editor")
            .pathParam("id",100)
            .when()
            .delete("/api/spartans/{id}")
            .then().log().all()
            .statusCode(HttpStatus.SC_FORBIDDEN)
            .body("message",is("Forbidden"));
    }
    @DisplayName("DELETE /api/spartans/{id} --> Admin expect 200 -->OK")
    @Test
    public void test4(){
        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .pathParam("id",1)
                .when()
                .delete("/api/spartans/{id}")
                .then().log().all()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }


}
