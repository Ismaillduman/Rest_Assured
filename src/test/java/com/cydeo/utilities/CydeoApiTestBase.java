package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class CydeoApiTestBase {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI="https://api.training.cydeo.com";
    }
    @AfterAll
    public static void destroy(){
        RestAssured.reset();
    }
}
