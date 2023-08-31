package com.cydeo.day10;

import com.cydeo.utilities.DB_Util;
import com.cydeo.utilities.SpartanAuTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_SpartanXMLTest extends SpartanAuTestBase {
    @DisplayName("spartan with xml")
    @Test
    void test1(){
        Response response = given().accept(ContentType.XML)

                .auth().basic("admin", "admin")

                .when()
                .get("/api/spartans")

                //.prettyPeek()

                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.XML)
                .body("List.item[0].name",is("Meade"))
                .body("List.item[1].name",is("Nels"))
                .extract().response();

        XmlPath xmlPath = response.xmlPath();

        String nameZero = xmlPath.getString("List.item[0].name");
        String nameLast = xmlPath.getString("List.item[-1].name");

        List<String> nameList = xmlPath.getList("List.item.name");
        System.out.println("nameList = " + nameList);


    }


}
