package com.cydeo.utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.*;

public class BookItUtils {

    public static String getToken(String email,String password){
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .when()
                .get("/sign")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath();

        String accessToken = jsonPath.getString("accessToken");

        return "Bearer "+accessToken;
    }
}
