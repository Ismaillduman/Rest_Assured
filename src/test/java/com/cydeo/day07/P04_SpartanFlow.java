package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;


public class P04_SpartanFlow extends SpartanTestBase {
    static int id;
    @Test
    @Order(1)
    public void postSpartan(){
        Map<String,Object> bodyRequestPost= new LinkedHashMap<>();
        bodyRequestPost.put("name","API Flow POST");
        bodyRequestPost.put("gender","Male");
        bodyRequestPost.put("phone",1231231231l);

        Response response = given().log().body().contentType(ContentType.JSON)
                .body(bodyRequestPost)
                .when().post("/api/spartans")
                .then().log().all().statusCode(HttpStatus.SC_CREATED).extract().response();
        JsonPath jsonPath = response.jsonPath();

      id= jsonPath.getInt("data.id");
        given().accept(ContentType.JSON)
                .pathParam("id",id)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name",is("API Flow POST"));
    }

    @Test
    @Order(2)
    public void getSpartanWithId(){

        given().accept(ContentType.JSON)
                .pathParam("id",id)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name",is("API Flow POST"));
    }

    @Test
    @Order(3)
    public void putSpartanWithId(){
        Map<String,Object> bodyRequestPost= new LinkedHashMap<>();
        bodyRequestPost.put("name","API PUT Flow");
        bodyRequestPost.put("gender","Female");
        bodyRequestPost.put("phone",3213213213l);

  given().log().body().contentType(ContentType.JSON)
          .pathParam("id",id)
                .body(bodyRequestPost)
                .when().put("/api/spartans/{id}")
                .then().log().all().statusCode(HttpStatus.SC_NO_CONTENT).extract().response();

/* given() .contentType(ContentType.JSON)
                .pathParam("id",id)

                .body(requestBodyMap)
                .when().put("/api/spartans/{id}")
                .then().statusCode(HttpStatus.SC_NO_CONTENT);*/

    }

     /*

    Create a Spartan Flow to run below testCases dynamically

   - POST     /api/spartans
            Create a spartan Map,Spartan class
                name = "API Flow POST"
                gender="Male"
                phone=1231231231l

            - verify status code 201
            - message is "A Spartan is Born!"
            - take spartanID from response and save as a global variable


    - GET  Spartan with spartanID     /api/spartans/{id}


             - verify status code 200
             - verify name is API Flow POST

    - PUT  Spartan with spartanID    /api/spartans/{id}

             Create a spartan Map
                name = "API PUT Flow"
                gender="Female"
                phone=3213213213l

             - verify status code 204

    - GET  Spartan with spartanID     /api/spartans/{id}


             - verify status code 200
             - verify name is API PUT Flow

    - DELETE  Spartan with spartanID   /api/spartans/{id}


             - verify status code 204

     - GET  Spartan with spartanID   /api/spartans/{id}


             - verify status code 404


    Challenges
       Create @Test annotated method for each Request
       Put them in order to execute as expected
                    - Use your googling skills
                    - How to run Junit5 Tests in order  ?



     */
}
