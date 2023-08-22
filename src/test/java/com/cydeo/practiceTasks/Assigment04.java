package com.cydeo.practiceTasks;

import com.cydeo.utilities.FormulaTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class Assigment04 extends FormulaTestBase {
    /*- Given accept type is json
- And path param driverId is alonso
- When user send request /drivers/{driverId}.json
- Then verify status code is 200
- And content type is application/json; charset=utf-8
 - And total is 1
- And givenName is Fernando
- And familyName is Alonso
- And nationality is Spanish
- Use JSONPATH*/
    @DisplayName("GET drivers.json hamCrest")
    @Test
    public void test1() {
        Response response = given().log().uri().accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when().get("/drivers/{driverId}.json")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType("application/json; charset=utf-8")

                .body("MRData.total", is("1"))
                .body("MRData.DriverTable.Drivers[0].givenName", is("Fernando"))
                .body("MRData.DriverTable.Drivers[0].familyName", is("Alonso"))
                .body("MRData.DriverTable.Drivers[0].nationality", is("Spanish"))
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
//Print givenName of Driver by using extract method after HamCrest
        /*- Convert Driver information to Java Structure
Map<String,Object> driverMap=jsonpath.getMap(“pathOfDriver”)*/


    }

    @DisplayName("GET drivers.json jsonPath")
    @Test
    public void test2() {
        Response response = given().log().uri().accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when().get("/drivers/{driverId}.json")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType("application/json; charset=utf-8")
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        jsonPath.getInt("MRData.total");
        assertEquals(1,jsonPath.getInt("MRData.total"));
        assertEquals("Fernando",jsonPath.getString("MRData.DriverTable.Drivers[0].givenName"));
        assertEquals("Alonso",jsonPath.getString("MRData.DriverTable.Drivers[0].familyName"));
        assertEquals("Spanish",jsonPath.getString("MRData.DriverTable.Drivers[0].nationality"));


        /* {
                    "driverId": "abate",
                    "url": "http://en.wikipedia.org/wiki/Carlo_Mario_Abate",
                    "givenName": "Carlo",
                    "familyName": "Abate",
                    "dateOfBirth": "1932-07-10",
                    "nationality": "Italian"
                }*/


    }

    @DisplayName("GET drivers.json javaStructure")
    @Test
    public void test3() {
        Response response = given().log().uri().accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when().get("/drivers/{driverId}.json")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType("application/json; charset=utf-8")
                .extract().response();
        JsonPath jsonPath = response.jsonPath();


        Map<String, Object> driversMap = jsonPath.getMap("MRData.DriverTable.Drivers[0]");
        /*   List<Object> driversList = jsonPath.getList("MRData.DriverTable.Drivers");
        System.out.println("driversList.get(0) = " + driversList.get(0));
       {
                    "driverId": "abate",
                    "url": "http://en.wikipedia.org/wiki/Carlo_Mario_Abate",
                    "givenName": "Carlo",
                    "familyName": "Abate",
                    "dateOfBirth": "1932-07-10",
                    "nationality": "Italian"
                }*/
        String driverId = (String) driversMap.get("driverId");
        String url = (String) driversMap.get("url");
        String givenName = (String) driversMap.get("givenName");
        String familyName = (String) driversMap.get("familyName");
        String dateOfBirth = (String) driversMap.get("dateOfBirth");
        String nationality = (String) driversMap.get("nationality");

        List<String> expectedDriversInfo = new ArrayList<>(Arrays.asList("alonso",
                "http://en.wikipedia.org/wiki/Fernando_Alonso", "Fernando", "Alonso", "1981-07-29", "Spanish"));
        List<String> driversInfo = new ArrayList<>(Arrays.asList
                (driverId, url, givenName, familyName, dateOfBirth, nationality));
      assertThat(driversInfo,is(expectedDriversInfo));

    }
}
