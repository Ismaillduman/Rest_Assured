package com.cydeo.day11;

import com.cydeo.utilities.ZippopotamTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P05_CsvFileSourceTest extends ZippopotamTestBase {

    @ParameterizedTest
    @CsvFileSource(resources = "/math.csv",numLinesToSkip = 1)
    public void test1(int n1,int n2,int total){
        System.out.println("n1 = " + n1);
        System.out.println("n2 = " + n2);
        System.out.println("total = " + total);


    }

    /**
     *    // Write a parameterized test for this request
     *     // Get the data from csv source called as --> zipcode.csv
     *     // state , city , numberOfPlace
     *     // GET https://api.zippopotam.us/us/{state}/{city}
     *     // After getting response numberOfPlaces needs to be same
     *
     *     state , city , numberOfPlace
     *     NY,New York,166
     *     CO,Denver,76
     *     VA,Fairfax,10
     *     MA,Boston,56
     *     MD,Annapolis,9
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/zipCode.csv",numLinesToSkip = 1)
    public void test(String state,String city,int numberOfPlace){
        System.out.println("state = " + state);
        System.out.println("city = " + city);
        System.out.println("numberOfPlace = " + numberOfPlace);

        given()
                .accept(ContentType.JSON)
                .pathParam("state",state)
                .pathParam("city",city)
                .when()
                .get("/us/{state}/{city}")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("places",hasSize(numberOfPlace));


    }

}
