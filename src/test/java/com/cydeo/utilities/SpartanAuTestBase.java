package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.reset;

public class SpartanAuTestBase {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://44.204.20.3:7000";
    }

    @AfterAll
    public static void destroy() {
        reset();
    }
}
