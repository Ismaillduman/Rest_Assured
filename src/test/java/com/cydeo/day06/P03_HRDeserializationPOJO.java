package com.cydeo.day06;

import com.cydeo.pojo.Region;
import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;


public class P03_HRDeserializationPOJO extends HrTestBase {
    @DisplayName("GET regions to desserializate to POJO - LOMBOK -JSON PROPERTY")
    @Test
    public void test1() {
        JsonPath jsonPath = get("/regions")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath();

        List<Region> regionList = jsonPath.getList("items", Region.class);
        System.out.println("regionList.get(0).getRegionName() = " + regionList.get(0).getRegionName());
        //get first region from items array and convert it to Region class
        Region region = jsonPath.getObject("items[0]", Region.class);
        System.out.println("region.getRegionName() = " + region.getRegionName());

        System.out.println("regionList.get(0).getLinks().get(0).getHref() = " + regionList.get(0).getLinks().get(0).getHref());

        System.out.println("region.getLinks().get(0).getHref() = " + region.getLinks().get(0).getHref());
    }


    /*
TASK

Given accept is application/json
When send request  to /regions endpoint
Then status should be 200
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
    -- Create Regions POJO
    -- And ignore field that you dont need


 */
    @DisplayName("GET regions to desserializate to POJO - LOMBOK -JSON PROPERTY")
    @Test
    public void test2() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when().get("/regions")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType("application/json").extract().jsonPath();
        Map<String, Object> allData = jsonPath.getMap("");
        List<Map<String,Object>> items= (List<Map<String, Object>>) allData.get("items");
        List<Object> regionId = items.stream().map(eachItem -> eachItem.get("region_id")).collect(Collectors.toList());
        List<Object> regionName = items.stream().map(eachItem -> eachItem.get("region_name")).collect(Collectors.toList());
        System.out.println("regionId = " + regionId);
        System.out.println("regionName = " + regionName);


        List<Region> items1 = jsonPath.getList("items", Region.class);
        System.out.println("items1.stream().map(eachItem->eachItem.getRegionId()).collect(Collectors.toList()) = " + items1.stream().map(eachItem -> eachItem.getRegionId()).collect(Collectors.toList()));
        System.out.println("items1.stream().map(eachRegion->eachRegion.getRegionName()).collect(Collectors.toList()) = " + items1.stream().map(eachRegion -> eachRegion.getRegionName()).collect(Collectors.toList()));

    }

}
