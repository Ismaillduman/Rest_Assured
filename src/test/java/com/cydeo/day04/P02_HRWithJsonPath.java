package com.cydeo.day04;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_HRWithJsonPath extends HrTestBase {

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).get("/countries");

        //verify status code
        assertEquals(200, response.statusCode());

        //create jsonpath object
        JsonPath jsonPath = response.jsonPath();

        //get me 3rd country name
        System.out.println("jsonPath.get(\"name[2]\") = " + jsonPath.getString("items[2].country_name"));
        //get me 3rd and 4th country name
        System.out.println("jsonPath.getString(\"items[2,3].country_name\") = " + jsonPath.getString("items[2,3].country_name"));
        //get me all country name where region_id is 2
        System.out.println("----------------------------------------");

        List<String> country_names= jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("country_names = " + country_names);

        /* items.findAll {it.region_id==2}.country_name

 items --> it is key for the array that we are getting from response.

      .findAll --> it is method that comes from Gpath to find all matching data

         {it.region_id==2} --> this is the condition that we are looking for to filter

               it-> iterator --> it will check each element from the array

                  .country_name --> data that we are looking for.

                after filter array of Json Object will prepare list of data based on your return type.*/



    }

      /*
    Given accept type is application/json
    And query param limit is 200
    When user send request /employees
    Then user should see ............

     */
    @DisplayName("user send request /employees")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).queryParam("limit", 200).when().get("/employees");
        //assert status code
        assertEquals(200,response.statusCode());
        //create jsonPath object
        JsonPath jsonPath = response.jsonPath();
        //get all emails from response
        List<String> emailList = jsonPath.getList("items.email");
        System.out.println(emailList);
        System.out.println("emailList.size() = " + emailList.size());
        //get all emails who is working as IT_PROG
        List<String> list = jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email");
        for (String s : list) {
            System.out.println("get all emails who is working as IT_PROG "+s);
        }
        //get me all employees first names whose salary is more than 10000

        List<String> first_names = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("first_names = " + first_names);

        //get me all information from response who has max salary
        System.out.println("jsonPath.getString(\"items.max{it.salary}\") =" +
                " " + jsonPath.getString("items.max {it.salary}"));

        //get me first name from response who has max salary
        System.out.println("jsonPath.getString(\"items.max.findAll{it.salary}first_name\") =" +
                " " + jsonPath.getString("items.max {it.salary}.first_name"));

        //    //get me firstname from response who has min salary
        System.out.println("jsonPath.getString(\"items.min {it.salary}first_name\") =" +
                " " + jsonPath.getString("items.min {it.salary}.first_name"));

    }

           /*

    TASK
    Given
             accept type is application/json
     When
             user sends get request to /locaitons
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK

      */
    @DisplayName(" get the second city with JsonPath")
@Test
    public void test3(){
        Response response = given().accept(ContentType.JSON).get("/locations");
        response.prettyPrint();
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        JsonPath jsonPath = response.jsonPath();

       assertEquals("Bern",jsonPath.getString("items[1].city"));
       assertEquals("Whitehorse",jsonPath.getString("items[-1].city"));
        System.out.println("jsonPath.getList(\"items.country_id\") = " + jsonPath.getList("items.country_id"));
        System.out.println("jsonPath.getList(\"items.findAll {it.country_id=='UK'}.city\") = " + jsonPath.getList("items.findAll {it.country_id=='UK'}.city"));

    }

}