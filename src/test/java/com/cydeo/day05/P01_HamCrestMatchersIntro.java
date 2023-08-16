package com.cydeo.day05;

import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class P01_HamCrestMatchersIntro {
    @Test
    public void test() {

        //junit 5 assert equals method
        assertEquals(9, 6 + 3);

        //HamcrestMatchers comes from Restassured dependency
        //2 static import to get rid of class names ,directly call assertThat and Matchers
        //import static org.hamcrest.MatcherAssert.*;
        //import static org.hamcrest.Matchers.*;

        //Hamcrest Matchers
        //Matchers has 2 overloaded methods
        //-First one will take value to check
        //-Second one will take another Matchers to make it readable / to add new assert functionality
        assertThat(6 + 3, is(9));
        assertThat(6 + 3, is(equalTo(9)));
        assertThat(6 + 3, equalTo(9));


        /**
         is(someValue)
         is(equalTo(someValue))
         equalTo(someValue)
         All of them same in terms of assertion
         */

        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(9)));
        assertThat(5 + 5, is(not(equalTo(9))));

    }

    @Test
    public void testStrings() {
        String msg = "API is fun!";

        assertThat(msg, is("API is fun!"));
        assertThat(msg, equalTo("API is fun!"));
        assertThat(msg, equalToIgnoringCase("api is fun!"));


        assertThat(msg, startsWith("API"));
        assertThat(msg, startsWithIgnoringCase("apI"));
        assertThat(msg, endsWith("!"));
        assertThat(msg, endsWithIgnoringCase("FUN!"));

        assertThat(msg, containsString("is"));
        assertThat(msg, containsStringIgnoringCase("IS"));

        assertThat(msg, not("FUN!"));
        assertThat(msg, is(not("FUN!")));


    }

    @Test
    public void testCollections() {
        List<Integer> number_list = Arrays.asList(3, 5, 2, 4, 7, 8, 95, 45);

        //how to check size of elements
        assertThat(number_list, hasSize(8));

        //how to check 95 is into the collection
        assertThat(number_list, hasItem(95));

        //how to check 44 and 76 is into the collection
        assertThat(number_list, hasItems(8, 45));

        //loop through each of the element and make sure they are matching with Matchers inside the everyItem
        assertThat(number_list, everyItem(greaterThanOrEqualTo(2)));

        // //check if you have values and their position is correct
        assertThat(number_list,containsInRelativeOrder(2,4,45));

        //check if you have all the values, position might be different

        assertThat(number_list,containsInAnyOrder(2, 5, 3, 45, 7, 8, 95, 4));

    }
}
