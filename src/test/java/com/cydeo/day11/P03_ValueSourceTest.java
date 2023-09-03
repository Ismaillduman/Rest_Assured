package com.cydeo.day11;

import com.cydeo.utilities.ZippopotamTestBase;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;

public class P03_ValueSourceTest extends ZippopotamTestBase {
    @ParameterizedTest
    @ValueSource(ints = {10,50,20,30,60})
    public void test1(int number){
        System.out.println("number = " + number);
        Assertions.assertTrue(number<40);
    }


    @ParameterizedTest(name = "{index} - verify name is {0}")
    @ValueSource(strings = {"Mike","Kevin","Ashly","Mark"})
    public void test1(String name){
       Assertions.assertTrue(name.length()>4);
    }

    //TASK
    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200

    @ParameterizedTest
    @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void zipcode(int zipCode){

        System.out.println("zipCode = " + zipCode);

        given().log().uri()
                .accept(ContentType.JSON)
                .pathParam("zipCode",zipCode)
                .when()
                .get("/us/{zipCode}")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
