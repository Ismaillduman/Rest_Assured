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

public class Assigment02 extends HrTestBase {

    /*
    - Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
And Region_id is 2
*/
    @Test
    public void test1() {

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("country_id", "US")
                .when()
                .get("/countries/{country_id}");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("US"));
        assertTrue(response.body().asString().contains("US"));

        response.prettyPrint();
    }

    /*
TASK 2 :
- Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is "Json"
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
*/
@DisplayName("users sends request to /employees with query param")
    @Test
    public void test2(){

    Response response = given().accept(ContentType.JSON).queryParam("q", "{\"department_id\":80}").when().get("/employees");
   // response.prettyPrint();
    assertEquals(200,response.statusCode());
    assertEquals("application/json",response.contentType());

    List<String> jobId = response.path("items.job_id");
    for (String eachID : jobId) {

           assertTrue(eachID.contains("SA"));
    }
    List<String> departmentId= response.path("items.department_id");
    for (String eachId : departmentId) {
        assertEquals("80", eachId);
    }

}

    /*
TASK 3 :
- Given accept type is Json
- Query param value q={“region_id":3}
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
*/
}
