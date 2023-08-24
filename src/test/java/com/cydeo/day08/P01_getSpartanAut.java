package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_getSpartanAut extends SpartanAuTestBase {

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


}
