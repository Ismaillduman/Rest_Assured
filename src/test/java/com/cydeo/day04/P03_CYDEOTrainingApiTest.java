package com.cydeo.day04;

import com.cydeo.utilities.CydeoApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_CYDEOTrainingApiTest extends CydeoApiTestBase {
    /*
      Given accept type is application/json
      And path param is 2
      When user send request /student/{id}
      Then status code should be 200
      And content type is application/json;charset=UTF-8
      And Date header is exist
      And Server header is envoy
      And verify following
                  firstName Mark
                  batch 13
                  major math
                  emailAddress mark@email.com
                  companyName Cydeo
                  street 777 5th Ave
                  zipCode 33222
      */
    @Test
    public void test() {


        Response response = given().accept(ContentType.JSON).pathParam("id", 2).when()
                .get("/student/{id}");
        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("envoy",response.header("server"));
        JsonPath jsonPath = response.jsonPath();

        boolean isDate = response.headers().hasHeaderWithName("Date");
        assertTrue(isDate);

        assertEquals("Mark", jsonPath.getString("students[0].firstName"));
       assertEquals(13,jsonPath.getInt("students[0].batch"));
       assertEquals("math",jsonPath.getString("students[0].major"));
       assertEquals("mark@email.com",jsonPath.getString("students[0].contact.emailAddress"));
       assertEquals("Cydeo",jsonPath.getString("students[0].company.companyName"));
       assertEquals("777 5th Ave",jsonPath.getString("students[0].company.address.street"));
       assertEquals("33222",jsonPath.getString("students[0].company.address.zipCode"));

/* And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222*/

    }
}
