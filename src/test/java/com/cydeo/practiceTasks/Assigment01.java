package com.cydeo.practiceTasks;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
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
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/employees/1");
        response.prettyPrint();
        assertEquals(404,response.statusCode());
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
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/regions/1");
        response.prettyPrint();
        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Europe"));
        boolean isDate=response.getHeaders().hasHeaderWithName("Date");

        assertTrue(isDate);

        String header = response.getHeader("Transfer-Encoding");
        assertEquals("chunked",header);
    }

}
