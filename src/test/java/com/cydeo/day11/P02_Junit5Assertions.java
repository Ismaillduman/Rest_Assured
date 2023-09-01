package com.cydeo.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class P02_Junit5Assertions {

    /**
     *
     * HARD ASSERT -->
     * - Test Execution will be aborted if the assert condition is not met
     * - Rest of the execution will stop
     * - Use Case --> if we are checking critical functionally of the app we can check with hard assert
     *
     */
    @Test
    public void hardAssert(){
        assertEquals(10,5+5);
        System.out.println("First assertion is done");
        assertEquals(10,5+3);
        System.out.println("Second assertion is done");
        assertEquals(10,5+5);
        System.out.println("Third assertion is done");
    }

    @Test
    public void softAssert(){
        assertAll("Soft assert",
                () -> assertEquals(10,5+5),
                () -> assertEquals(10,5+3),
                () -> assertEquals(10,5+4));
    }

}
