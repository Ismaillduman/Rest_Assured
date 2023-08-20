package com.cydeo.liveReview.week02;

import com.cydeo.pojo.MRData;

import com.cydeo.pojo.Status;
import com.cydeo.pojo.StatusTable;
import com.cydeo.utilities.FormulaTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class P05_DeserializationPOJO extends FormulaTestBase {
    
        /*
        - ERGAST API
        - Given accept type is json
        - When user send request /status.json
        - Then verify status code is 200
        - And content type is application/json; charset=utf-8
        - And total is 137
        - And limit is 30
        - And each status has statusId
     */

    @Test
    public void statusPOJO() {
        JsonPath jsonPath = given().log().uri()
                .when().get("/status.json")
                .then().statusCode(HttpStatus.SC_OK)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();

        MRData mrData = jsonPath.getObject("MRData", MRData.class);
        System.out.println("mrData = " + mrData);


        StatusTable statusTable = mrData.getStatusTable();
        System.out.println("statusTable = " + statusTable);

        List<Status> statusList = statusTable.getStatusList();
        System.out.println("statusList = " + statusList);

        System.out.println("statusList.get(0).getStatusId() = " + statusList.get(0).getStatusId());

    }
}