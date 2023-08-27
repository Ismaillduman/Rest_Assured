package com.cydeo.day08;

import com.cydeo.utilities.BookItTestBase;
import com.cydeo.utilities.BookItUtils;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P02_bookItTestAutToken extends BookItTestBase {

    String email = "lfinnisz@yolasite.com";
    String password = "lissiefinnis";

    String accessToken = BookItUtils.getToken(email, password);


    @DisplayName("GET /api/campuses")
    @Test
    public void test1() {

        System.out.println("accessToken = " + accessToken);

        given()
                .accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .when()
                .get("/api/campuses").prettyPeek()
                .then().statusCode(HttpStatus.SC_OK);
    }

    @DisplayName("GET /api/campuses")
    @Test
    public void test2() {

        System.out.println("accessToken = " + accessToken);

        given()
                .accept(ContentType.JSON)
                .header("Authorization",BookItUtils.getToken(email,password))
                .when()
                .get("/api/campuses").prettyPeek()
                .then().statusCode(HttpStatus.SC_OK);
    }

    @DisplayName("GET /api/users/me")
    @Test
    public void test3() {

        System.out.println("accessToken = " + accessToken);

        given()
                .accept(ContentType.JSON)
                .auth().oauth2(accessToken)
                .when()
                .get("/api/users/me").prettyPeek()
                .then().statusCode(HttpStatus.SC_OK);
    }



}
