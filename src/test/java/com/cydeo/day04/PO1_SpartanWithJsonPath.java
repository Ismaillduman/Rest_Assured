package com.cydeo.day04;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import  static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class PO1_SpartanWithJsonPath extends SpartanTestBase {
    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */
    @DisplayName("user sends a get request with jsonpath")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).and()
                .pathParam("id", 10).when().get("api/spartans/{id}");

        JsonPath jsonPath = response.jsonPath();

        int id=jsonPath.getInt("id");

        String name=jsonPath.getString("name");

        String gender=jsonPath.getString("gender");

        long phone=jsonPath.getLong("phone");
        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(3312820936l,phone);


    }
}
