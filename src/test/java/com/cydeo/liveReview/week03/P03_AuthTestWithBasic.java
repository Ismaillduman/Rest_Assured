package com.cydeo.liveReview.week03;

import com.cydeo.utilities.SpartanAuTestBase;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P03_AuthTestWithBasic extends SpartanAuTestBase {

@Test
    void basic_auth(){

    given().auth().basic("user","user")
            .when()
            .get("/api/spartans")
            .then()
            .statusCode(200);
}

    @Test
    void negative_basic_auth(){

        given().auth().basic("users","user")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(401);
    }

    @Test
    void basic_auth_in_header(){

        given()
                //auth()
                //.basic("user","user")
                .header("Authorization","Basic dXNlcjp1c2Vy")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200);
    }
}
