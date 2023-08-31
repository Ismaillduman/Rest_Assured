package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class HrTestBase {
    @BeforeAll
    public static void  init(){
        RestAssured.baseURI="http://54.158.239.73:1000/ords/hr";
    }

    @AfterAll
    public static void destroy(){
        RestAssured.reset();
    }
}
