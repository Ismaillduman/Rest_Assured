package com.cydeo.practiceTasks;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Assigment01 extends HrTestBase {
    /*Task 1 :
    - Given accept type is Json
    - When users sends request to /countries/US
    - Then status code is 200
    - And Content - Type is application/json
    - And response contains United States of America*/
    @DisplayName("users sends request to /countries/US")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().when().get("/countries/US");
        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("United States of America"));

    }

    /*Task 2 :

- Given accept type is Json
- When users sends request to /employees/1
- Then status code is 404

Task 3 :
- Given accept type is Json
- When users sends request to /regions/1
- Then status code is 200
- And Content - Type is application/json
- And response contains Europe
- And header should contains Date
- And Transfer-Encoding should be chunked*/

    @DisplayName("users sends request to /employees/1")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/employees/1");
        response.prettyPrint();
        assertEquals(404, response.statusCode());
    }

    /*Task 3 :
    - Given accept type is Json
    - When users sends request to /regions/1
    - Then status code is 200
    - And Content - Type is application/json
    - And response contains Europe
    - And header should contains Date
    - And Transfer-Encoding should be chunked*/
    @DisplayName("users sends request to /regions/1")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/regions/1");
        response.prettyPrint();
        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Europe"));
        boolean isDate = response.getHeaders().hasHeaderWithName("Date");

        assertTrue(isDate);

        String header = response.getHeader("Transfer-Encoding");
        assertEquals("chunked", header);

        // - And response region_name Europe

        assertEquals("Europe", response.path("region_name"));

        // - And Third link rel is "describedby"
        assertEquals("describedby", response.path("links[2].rel"));

        // get link details
        System.out.println("response.path(\"links[3].href\") = " + response.path("links[3].href"));

        // get me last object of links rel information
        System.out.println("response.path(\"links[-1].rel\") = " + response.path("links[-1].rel"));

        // get all rels by using response path

        List<Map<String,String>> all_links = response.path("links");
        for (Map<String, String> each_link : all_links) {
            System.out.println("each_link_rel = " + each_link.get("rel"));
        }
        // Find all href that ends with regions/1 by using links arraylist and stream
        for (Map<String, String> each_link : all_links) {
           if(each_link.get("href").endsWith("regions/1"))
           {
               System.out.println("each_link.get(\"href\") = " + each_link.get("href"));
           }
        }
    }

}
