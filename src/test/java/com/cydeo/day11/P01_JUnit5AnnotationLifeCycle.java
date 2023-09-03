package com.cydeo.day11;

import org.junit.jupiter.api.*;

public class P01_JUnit5AnnotationLifeCycle {

    //TestNG -->@BeforeClass

    /*: @BeforeAll and AfterAll They must be static: Because these methods are executed
     before any instances of the class are created,
     it's not possible to invoke them on an instance of the class.
      That's why these methods must be declared as static
      since static methods can be called without the need for
      an instance of the class..*/
    @BeforeAll
    static void init(){

        System.out.println("BeforeAl is running");
    }

    @BeforeEach

     public void initEach(){

        System.out.println("BeforeEach is running");
    }
    @Disabled
    @Test
    public void test2(){

        System.out.println("Test2 disabled is running");
    }
    @Test
    public void test1(){

        System.out.println("Test1 is running");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("After each is running");
    }
    @AfterAll
    public static void afterAll(){
        System.out.println("After all is running");
    }
}
