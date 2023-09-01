package com.cydeo.day11;

import com.cydeo.utilities.ZippopotamTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P04_CsvSource extends ZippopotamTestBase {
    @ParameterizedTest
    @CsvSource({"1,3,4",
            "2,3,5",
            "3,4,8",
            "5,6,11"})
    public void test(int num1,int num2,int sum){
        System.out.println(num1 +"+"+num2+"="+sum);
        Assertions.assertEquals(sum,(num1+num2));
    }

    //TASK
    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "MA, Boston",
        "MD, Annapolis"
     */
    //verify each of the place name contains your city name
    //print number of places for each request

    @ParameterizedTest
    @CsvSource({"NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "MA, Boston",
            "MD, Annapolis"
    })
    public void test1(String state,String city){

        int placeNum = given().accept(ContentType.JSON)
                .log().uri()
                .pathParam("state", state)
                .pathParam("city", city)
                .when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places.'place name'", everyItem(containsString(city)))
                .extract().jsonPath().getList("places").size();
        System.out.println("placeNum = " + placeNum);

    }
}
