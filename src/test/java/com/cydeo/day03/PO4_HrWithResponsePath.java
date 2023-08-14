package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import static io.restassured.internal.common.assertion.Assertion.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class PO4_HrWithResponsePath extends HrTestBase {
    @Test
    public void test() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");
        response.prettyPrint();


        //print limit
        System.out.println("response.path(\"Limit\") = " + response.path("Limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        //print second country name
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));
        //print 4th element country name
        System.out.println("response.path(\"items[3].country_name\") = " + response.path("items[3].country_name"));
        //print 3rd element href
        System.out.println("response.path(\"items[2].links[1].href\") = " + response.path("items[2].links[1].href"));


        //get all countries names
        List<String> allCountry = response.path("items.country_name");
        for (String each : allCountry) {
            System.out.println("each = " + each);
        }
        //verify all region_ids equals to 2
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        List<Integer> allRegionId = response.path("items.region_id");
        for (Integer each : allRegionId) {
            assertEquals(2,each);
        }
    }
}
