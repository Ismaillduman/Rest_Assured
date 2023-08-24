package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SpartanAuTestBase {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://44.204.20.3:7000";
    }
}
