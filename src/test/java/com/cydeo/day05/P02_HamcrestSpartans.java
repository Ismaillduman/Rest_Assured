package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class P02_HamcrestSpartans extends SpartanTestBase {
    /*   /*
    Given accept type is Json
    And path param id is 15
    When user sends a get request to api/spartans/{id}
    Then status code is 200
    And content type is Json
    And json data has following
        "id": 15,
        "name": "Meta",
        "gender": "Female",
        "phone": 1938695106
     */
    @Test
    public void test() {
      given().accept(ContentType.JSON).pathParam("id",15)
              .when().get("api/spartans/{id}").then()
              .statusCode(HttpStatus.SC_OK)
              .contentType("application/json")
              .body("id",is(15),"name",is("Meta")
              ,"gender",is("Female"),"phone",is(1938695106));
    }

    @Test
    public void test2(){
        given().accept(ContentType.JSON).pathParam("id",15)
                .when().get("api/spartans/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .contentType("application/json")
                .and()
                .assertThat()
                .body("id",is(15))
                .and()
                .body("name",is("Meta"))
                .body("gender",is("Female"))
                .body("phone",is(1938695106));
    }
    /*  /*
            REQUEST
                given().
                        log().all() --> it will give all information about your request(path/query params,URI,body etc)
                        .method()
                        .uri()
                        .parameters()
                        .body()....
            RESPONSE
                then()
                        .log().all() --> it will give all response information

                ifValidationFails() --> it will print all information when there is a failure
                ifValidationFails(LogDetail.HEADERS) --> after failure only show me headers
                we can change LogDetail scope to see other information.
                and we can use ifValidationFails for both request and response logs.
         */

        /*
            HOW TO PRINT RESPONSE BODY

                response.prettyPrint() (String) --> it is printing response body into screen
                response.prettyPeek() (Response) --> it will print response intro screen, returns Response
                and allows us to continue chaining
         */
        /*
            HOW TO EXTRACT DATA AFTER DOING VALIDATION WITH THEN() and HAMCREST?
            -- extract() --> method will help us to STORE data after doing verification
            in following type
                response() --> to get response object ex: extract().response();
                OR
                jsonPath() --> to get response body as jsonpath object directly
                   ex: extract().jsonPath();


         */

    @Test
    public void test3(){
        JsonPath jsonPath = given().
                log().ifValidationFails().
                accept(ContentType.JSON).pathParam("id", 15)
                .when().get("api/spartans/{id}").prettyPeek()

                .then()
                .log().ifValidationFails(LogDetail.HEADERS)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .contentType("application/json")
                .and()
                .assertThat()
                .body("id", is(15))
                .and()
                .body("name", is("Meta"))
                .body("gender", is("Female"))
                .body("phone", is(1938695106))
                .extract().jsonPath();

        int id = jsonPath.getInt("id");
        String name=jsonPath.get("name");
        System.out.println(id);

        //expected data from database
        //using db utils, saving variables
        int expectedIdDb = 15;
        String expectedNameDb = "Meta";

        //compare api vs database
        //we can use hamcrest or junit5 assertion
        assertThat(id,is(expectedIdDb));
        assertThat(name,is(equalTo(expectedNameDb)));

        //junit 5
        Assertions.assertEquals(expectedIdDb,id);
        Assertions.assertEquals(expectedNameDb,name);

         /*
            Why we need to extract, while we can complete all of verification(status code,header,body)
            with then() and hamcrest mathcers?

            --Assume that we are going to do verification against DB/UI. In that case I need to get data from API
                After completing my api verification
                So we need to sometimes list of names / ids / whatever field we have to check against to db or UI
                That is why we need to initialize as Response or JsonPath to get related data from api that we want to verify.

         */


    }
}
