package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class CydeoApiTestBase {
    @BeforeAll
    public void init(){
        RestAssured.baseURI="https://44.204.20.3"
    }
}
