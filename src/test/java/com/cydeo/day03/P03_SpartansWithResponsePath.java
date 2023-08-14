package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartansWithResponsePath extends SpartanTestBase {
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
    @DisplayName("Get spartans with response path")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", "10")
                .when().get("/api/spartans/{id}");

        response.prettyPrint();
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");
        List<Object> actualData = new ArrayList<>(Arrays.asList(id, name, gender, phone));
        List<Object> expectedData = new ArrayList<>(Arrays.asList(10, "Lorenza", "Female", 3312820936l));
        assertEquals(expectedData, actualData);

        //if the path is not correct it will return null

    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON).when().get("/api/spartans");
        //response.prettyPrint();

        int firstID = response.path("id[0]");
        int IDFirst = response.path("[0].id");
        System.out.println("firstID = " + firstID);
        System.out.println("IDFirst = " + IDFirst);


        //get first spartan name
        System.out.println("response.path(\"name[0]\") = " + response.path("name[0]"));
        System.out.println("response.path(\"name[1]\") = " + response.path("name[1]"));
        System.out.println("response.path(\"name[2]\") = " + response.path("name[2]"));

        //name[-1]-->-1 refers last element of the name list
        System.out.println("response.path(\"name[-1]\") = " + response.path("name[-1]"));


        List<String> allName = response.path("name");
        int count = 0;
        for (String eachName : allName) {
            if (eachName.startsWith("A")) {
                count++;
                System.out.println(eachName);
            }
        }
        System.out.println("count = " + count);

    }


}
