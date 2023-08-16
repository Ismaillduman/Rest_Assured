package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class ZippopotamTestBase {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI="https://www.zippopotam.us";
    }
}
