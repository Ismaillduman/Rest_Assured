package com.cydeo.day10;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P02_MovieXML {

    @Test
    void test1(){
        RestAssured.baseURI="http://www.omdbapi.com";
        Response response = given().
                queryParams("apikey", "e8c33115")
                .queryParam("r", "xml")
                .queryParam("t", "Inception")
                .when()
                .get("http://www.omdbapi.com").prettyPeek();

        XmlPath xmlPath = response.xmlPath();

        //get me year attribute

        System.out.println("xmlPath.getString(\"root.movie.year\") = " + xmlPath.getString("root.movie.@year"));

        //get me year title
        System.out.println("xmlPath.getString(\"root.movie.@title\") = " + xmlPath.getString("root.movie.@title"));

        //get me director

        System.out.println("xmlPath.getString(\"root.movie.@director\") = " + xmlPath.getString("root.movie.@director"));

        //get me imdb rating
        System.out.println("xmlPath.getString(\"root.movie.@director\") = " + xmlPath.getString("root.movie.@imdbRating"));
    }

    @Test
    void test2(){
        RestAssured.baseURI="http://www.omdbapi.com";
        Response response = given().
                queryParams("apikey", "e8c33115")
                .queryParam("r", "xml")
                .queryParam("s", "Harry Potter")
                .when()
                .get("http://www.omdbapi.com").prettyPeek();

        XmlPath xmlPath = response.xmlPath();
        List<String> yearList = xmlPath.getList("root.result.@year");
        for (String year : yearList) {
            System.out.println("s = " + year);
            assertThat(year,is(greaterThan("2000")));
        }
        List<String> titleList = xmlPath.getList("root.result.@title");

        for (String eachTitle : titleList) {
            assertThat(eachTitle,is(containsStringIgnoringCase("Harry Potter")));
        }

        String totalResult = xmlPath.getString("root.@totalResults");
        System.out.println("totalResult = " + totalResult);
        assertThat(totalResult,is(equalTo("134")));


    }
    /**
     * http://www.omdbapi.com?apikey=81815fe6&r=xml&s=Harry Potter
     * --Try to get all years and verify they are greater then 2000
     * --Print each title and make sure each of them contains Harry Potter
     * --verify total result is 127
     */
}
