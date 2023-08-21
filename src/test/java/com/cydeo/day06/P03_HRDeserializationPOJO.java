package com.cydeo.day06;

import com.cydeo.pojo.Region;
import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
}
