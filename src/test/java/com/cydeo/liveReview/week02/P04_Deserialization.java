package com.cydeo.liveReview.week02;

import com.cydeo.utilities.FruitTestBase;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class P04_Deserialization extends FruitTestBase {
    /**
     * Send request to FruitAPI url and save the response
     * Accept application/json
     * GET /customers
     * Store the response in Response Object that comes from get Request
     * Print out followings
     *     - Print response
     *     - Content-Type is application/json
     *     - Status Code is 200
     *     - Retrieve data as JAVA Collections and print out following informations
     *
     System.out.println("====== GET META ======");
     System.out.println("====== GET LIMIT ======");
     System.out.println("====== GET CUSTOMERS ======");
     System.out.println("====== GET FIRST CUSTOMER ======");
     System.out.println("====== PRINT CUSTOMERS IDs ======");
     System.out.println("====== PRINT CUSTOMERS Names ======");

     *
     */


        @Test
        public void getCustomers() {

            Response response = given().log().uri().accept(ContentType.JSON).
                    when().get("/customers").prettyPeek().
                    then().statusCode(200)
                    .contentType(ContentType.JSON)
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();

            Map<String,Object> allData= jsonPath.getMap("");
            System.out.println("allData = " + allData);
            System.out.println("====== GET META ======");

            Map<String,Integer> meta= (Map<String, Integer>) allData.get("meta");
            System.out.println("meta = " + meta);

            System.out.println("====== GET LIMIT ======");



            System.out.println(meta.get("limit"));

            System.out.println("====== GET CUSTOMERS ======");

            List<Map<String,Object>> customers = (List<Map<String, Object>>) allData.get("customers");

            System.out.println("customers = " + customers);
            System.out.println("====== GET FIRST CUSTOMER ======");

            System.out.println("customers.get(0) = " + customers.get(0));
            Map<String,Object> firstCustomer=customers.get(0);
            System.out.println("====== PRINT CUSTOMERS IDs ======");
            System.out.println("firstCustomer.get(\"id\") = " + firstCustomer.get("id"));

            for (Map<String, Object> each_customer : customers) {
                System.out.println("customers_id "+each_customer.get("id"));
            }

            List<Object> all_id = customers.stream().map(eachCustomer -> eachCustomer.get("id")).collect(Collectors.toList());
            System.out.println("all_id = " + all_id);
            System.out.println("====== PRINT CUSTOMERS Names ======");

            System.out.println("jsonPath.get(\"customers.name\") = " + jsonPath.get("customers.name"));
            List<String> allName= new ArrayList<>();
            for (Map<String, Object> each_customer : customers) {
                allName.add(each_customer.toString());
            }
            System.out.println("allName = " + allName);
            List<Object> all_name = customers.stream().map(eachCustomer -> eachCustomer.get("name")).collect(Collectors.toList());
                System.out.println("all_name = " + all_name);


        }
    }
