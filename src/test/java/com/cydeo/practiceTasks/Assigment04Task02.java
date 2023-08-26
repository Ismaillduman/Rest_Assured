package com.cydeo.practiceTasks;

import com.cydeo.pojo.Constructors;
import com.cydeo.pojo.MRData;
import com.cydeo.utilities.FormulaTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.groovy.util.Arrays;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class Assigment04Task02 extends FormulaTestBase {
    /*TASK 2 : Solve same task with 4 different way
    - Use JSONPATH
    int total = jsonpath.getInt(“pathOfField”)
    - Use HAMCREST MATCHERS
    then().body(..........)
     Print all names of constructor by using extract method after HamCrest
    - Convert Constructor information to Java Structure
    List<Map<String,Object>> constructorMap=jsonpath.getList(“pathOfConsts”);
    - Convert Constructor information POJO Class
     List<ConstructorPOJO>
     constr=getObject(“pathOfConstr”,ConstructorPOJO.class)
    NOTE
    —> There is a class in JAVA That’s why give your class name
    ConstrutorPOJO to separate from existing one. Wrong imports may cause
    issue
    - Given accept type is json
    - When user send request /constructorStandings/1/constructors.json
    - Then verify status code is 200
    - And content type is application/json; charset=utf-8
    - And total is 17
    - And limit is 30
    - And each constructor has constructorId
    - And constructor has name
    - And size of constructor is 17
    - And constructor IDs has “benetton”, “mercedes”,”team_lotus”*/

    @DisplayName("with Hamcrest")
    @Test
    public void test1() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when().get("/constructorStandings/1/constructors.json").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK).
                contentType("application/json; charset=utf-8")
                .body("MRData.total",is("17"))
                .body("MRData.limit",is("30"))
                .body("MRData.ConstructorTable.Constructors.constructorId",everyItem(is(notNullValue())))
                .body("MRData.ConstructorTable.Constructors.name",everyItem(is(notNullValue())))
                .body("MRData.ConstructorTable.Constructors",hasSize(17))
                .body("MRData.ConstructorTable.Constructors.constructorId",containsInRelativeOrder("benetton", "mercedes","team_lotus"))
                .extract().jsonPath();

        int total = jsonPath.getInt("MRData.total");
        System.out.println("total = " + total);
        Map<String, Object> mrData = jsonPath.getMap("MRData");
        System.out.println("mrData = " + mrData);

    }


    @DisplayName("with jsonPath")
    @Test
    public  void test2() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when().get("/constructorStandings/1/constructors.json").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK).
                contentType("application/json; charset=utf-8")
                .extract().jsonPath();

        String actualTotal = jsonPath.getString("MRData.total");

        assertThat(actualTotal,is(equalTo("17")));
        assertThat(jsonPath.get("MRData.limit"),is(equalTo("30")));

        List<Map<String,Object>> listOfConstructors = jsonPath.getList("MRData.ConstructorTable.Constructors");

        for (Map<String, Object> eachConstructor : listOfConstructors) {
            //eachConstructor.containsKey("constructorId");
           assertThat(eachConstructor.get("constructorId"),notNullValue());

        }

        for (Map<String, Object> eachConstructor : listOfConstructors) {
            //eachConstructor.containsKey("name");
            assertThat(eachConstructor.get("name"),notNullValue());
        }
        int count=0;
        for (Map<String, Object> eachConstructor : listOfConstructors) {
            count++;
        }
        assertThat(count,is(17));
        assertThat(listOfConstructors.size(),is(17));

        //- And constructor IDs has “benetton”, “mercedes”,”team_lotus”
        List<String> constructorIdList=new ArrayList<>();
        for (Map<String, Object> eachConstructor : listOfConstructors) {
            constructorIdList.add((String) eachConstructor.get("constructorId"));
        }

        assertThat(constructorIdList,containsInRelativeOrder("benetton", "mercedes", "team_lotus"));
    }

    @DisplayName("with Pojo")
    @Test
    public  void test3() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when().get("/constructorStandings/1/constructors.json").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK).
                contentType("application/json; charset=utf-8")
                .extract().jsonPath();

        MRData mrData = jsonPath.getObject("MRData", MRData.class);

        System.out.println("mrData.getLimit() = " + mrData.getLimit());


        assertThat(mrData.getTotal(),is(equalTo("17")));
        assertThat(mrData.getLimit(),is(equalTo("30")));

        for (Constructors constructor : mrData.getConstructorTable().getConstructors()) {
           assertThat(constructor.getConstructorId(),notNullValue());
        }



        for (Constructors constructor : mrData.getConstructorTable().getConstructors()) {
            //eachConstructor.containsKey("name");
            assertThat(constructor.getName(),notNullValue());
        }


        assertThat( mrData.getConstructorTable().getConstructors().size(),is(17));

        //- And constructor IDs has “benetton”, “mercedes”,”team_lotus”
        List<String> constructorIdList=new ArrayList<>();
        for (Constructors constructor : mrData.getConstructorTable().getConstructors()) {
            constructorIdList.add( constructor.getConstructorId());
        }

        assertThat(constructorIdList,containsInRelativeOrder("benetton", "mercedes", "team_lotus"));
    }
}
