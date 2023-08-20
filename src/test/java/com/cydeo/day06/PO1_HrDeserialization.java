package com.cydeo.day06;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
public class PO1_HrDeserialization extends HrTestBase {
    /**
     * Create a test called getLocation
     * 1. Send request to GET /locations
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
     * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     */
    @DisplayName(" GET /locations")
    @Test
    public void test(){
        Response response = given().log().ifValidationFails().accept(ContentType.JSON)
                .get("/locations")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .contentType("application/json")
                .extract().response();

        //Map<Map<String,Object>,Map<String,Object>> locationMap = response.as(Map.class);

        JsonPath jsonPath = response.jsonPath();


//* System.out.println("====== GET FIRST LOCATION  ======");
        Map<String, Object> locations_items = jsonPath.getMap("items[0]");
        System.out.println("locations_items = " + locations_items);
//     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
        Map<String, Object> firstItems_links = jsonPath.getMap("items[0].links[0]");
        System.out.println("firstItems_links = " + firstItems_links);

//     * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
        List<Map<String,Object>> items_list_of_map = jsonPath.getList("items");
//        for (Map<String, Object> each_items : items_list_of_map) {
//            System.out.println("each_items = " + each_items);
//        }
//     * System.out.println("====== FIRST LOCATION ======");
        System.out.println("items_list_of_map.get(0) = " + items_list_of_map.get(0));
//     * System.out.println("====== FIRST LOCATION ID ======");
        System.out.println("locations_items.get(\"location_id\") = " + locations_items.get("location_id"));
//     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
        System.out.println("locations_items.get(\"location_id\") = " + locations_items.get("country_id"));
//     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
       List<Map<String,Object>>links = (List<Map<String, Object>>) items_list_of_map.get(0).get("links");
        System.out.println("links.get(0).get(\"href\") = " + links.get(0).get("href"));

        //Object[] array={locations_items,firstItems_links};

    }
}
