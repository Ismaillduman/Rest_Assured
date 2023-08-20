package com.cydeo.day05;

import com.cydeo.utilities.HrTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_DeserializationToCollections extends SpartanTestBase {
 /*
     Given accept type is application/json
     And Path param id = 10
     When I send GET request to /api/spartans
     Then status code is 200
     And content type is json
     And spartan data matching:
         id > 10
         name>Lorenza
         gender >Female
         phone >3312820936
     */
@Test
    public void test(){
    Response response = given().accept(ContentType.JSON)
            .pathParam("id", 10)
            .when().get("/api/spartans/{id}")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .contentType("application/json")
            .and()
            .assertThat()
            .body("id", is(10))
            .body("name", is("Lorenza"))
            .body("gender", equalTo("Female"))
            .body("phone", is(3312820936l))
            .extract().response();


// Solution 1 -> using response as method
    Map<String,Object> spartanMap = response.as(Map.class);
    int id = (int) spartanMap.get("id");
    String name= (String) spartanMap.get("name");
    //Solution 2 --> JsonPath
    JsonPath jsonPath = response.jsonPath();
        Map<String, Object> jsonPathMap = jsonPath.getMap("");
    int idJsonPath = (int) jsonPathMap.get("id");
    String nameJsonPath = (String) jsonPathMap.get("name");


}

@Test
    public void test2(){

    Response response = given().accept(ContentType.JSON)
            .when().get("/api/spartans/")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType("application/json")
            .extract().response();
    
    //Approach 1 --> with response object

    List<Map<String,Object>> spartan_list= response.as(List.class);

    for (Map<String, Object> map : spartan_list) {
        System.out.println("spartan_map = " + map);
    }

    //how to find first spartan info
    System.out.println("spartan_list.get(0) = " + spartan_list.get(0));

    //how to find first spartan name
    System.out.println("name "+spartan_list.get(0).get("name"));

    //how to find first spartan id
    System.out.println("id "+spartan_list.get(0).get("id"));



    //Approach 2 -- JSONPATH
    JsonPath jsonPath= response.jsonPath();
    List<Map<String,Object>> spartan_list_jsonPath= jsonPath.getList("");


    //how to find first spartan info
    System.out.println("first spartan info "+spartan_list_jsonPath.get(0));
    //how to find first spartan name
    System.out.println("first spartan name "+spartan_list_jsonPath.get(0).get("name"));
    //how to find first spartan id
    System.out.println("first spartan id "+spartan_list_jsonPath.get(0).get("id"));

}
}
