package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BookItTestBase {
    @BeforeAll
    public static void BookItTestBase(){
        RestAssured.baseURI="https://api.qa.bookit.cydeo.com";
    }
    @AfterAll
    public static void destroy(){
        RestAssured.reset();
    }
}
