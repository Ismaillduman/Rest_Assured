package com.cydeo.practiceTasks;

import com.cydeo.utilities.ZippopotamTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Assigment03 extends ZippopotamTestBase {
    /*Link —-> https://www.zippopotam.us/#
TASK 1
Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
post code is 22031
country is United States
country abbreviation is US
place name is Fairfax state is Virginia
*/

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 22031)
                .get("/us/{zipcode}");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals("cloudflare", response.getHeader("Server"));
        assertTrue(response.getHeaders().hasHeaderWithName("Report-To"));

        JsonPath jsonPath = response.jsonPath();
        assertEquals("22031", response.path("'post code'"));
        assertEquals("United States", jsonPath.getString("country"));
        assertEquals("US", response.path("'country abbreviation'"));
        assertEquals("Fairfax", response.path("places[0].'place name'"));
        assertEquals("Virginia", response.path("places[0].'state'"));

    }


    /*
    TASK 2
Given Accept application/json
And path zipcode is 50000
When I send a GET request to /us endpoint
Then status code must be 404
And content type must be application/json

*/
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 50000)
                .get("/us/{zipcode}");
        assertEquals(404, response.statusCode());
        assertEquals("application/json", response.contentType());

    }


    /*
TASK 3
Given Accept application/json
And path state is va
And path city is fairfax
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And payload should contains following information
country abbreviation is US
country United States
place name Fairfax
each places must contains fairfax as a value each post code must start with 22*/

    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("state", "va")
                .and().pathParam("city", "fairfax")
                .get("/us/{state}/{city}");
        //response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        JsonPath jsonPath = response.jsonPath();
        assertEquals("US", response.path("'country abbreviation'"));
        assertEquals("United States", response.path("'country'"));
        assertEquals("Fairfax", response.path("places[0].'place name'"));


        JsonPath jsonPath1 = response.jsonPath();
        List<String> listPlaceName = jsonPath1.getList("places.'place name'");
        assertTrue(listPlaceName.contains("Fairfax"));
        List<String> listPostCode = jsonPath1.getList("places.'post code'");
        for (String eachPostCode : listPostCode) {
            boolean isStart = eachPostCode.startsWith("22");
            assertTrue(isStart);
        }


    }
}
