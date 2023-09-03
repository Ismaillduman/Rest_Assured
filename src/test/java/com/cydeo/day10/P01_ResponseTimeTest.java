package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_ResponseTimeTest extends SpartanAuTestBase {
    @DisplayName("Response_time_with_spartans")
    @Test
    void test(){

        Response response = given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(both(greaterThan(1000L)).and(lessThan(3000L)))
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());
        System.out.println("response.getTimeIn(TimeUnit.MICROSECONDS) = " + response.getTimeIn(TimeUnit.MICROSECONDS));
    }
}
