package com.cydeo.day07;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class P02_SpartanPost extends SpartanTestBase {
    /**/

    /**
     * Given accept type is JSON
     * And Content type is JSON
     * And request json body is:
     * {
     * "gender":"Male",
     * "name":"John Doe",
     * "phone":8877445596
     * }
     * When user sends POST request to '/api/spartans'
     * Then status code 201
     * And content type should be application/json
     * And json payload/response/body should contain:
     * verify the success value is 'A Spartan is Born!'
     * "name": "John Doe",
     * "gender": "Male",
     * "phone": 8877445596
     */
    @DisplayName("POST new spartan with String body")
    @Test
    public void test1() {
        String bodyRequest = "{\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"John Doe\",\n" +
                "     \"phone\":8877445596\n" +
                "     }";
        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyRequest)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        assertThat(jsonPath.getString("data.name"), is("John Doe"));
        assertThat(jsonPath.getString("data.gender"), is("Male"));
        assertThat(jsonPath.getLong("data.phone"), is(8877445596l));

    }

    @DisplayName("POST new spartan with Map body")
    @Test
    public void test2() {
        Map<String, Object> newSpartan = new HashMap<>();
        newSpartan.put("gender", "Male");
        newSpartan.put("name", "John Doe");
        newSpartan.put("phone", 8877445596l);
        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(newSpartan)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        assertThat(jsonPath.getString("data.name"), is("John Doe"));
        assertThat(jsonPath.getString("data.gender"), is("Male"));
        assertThat(jsonPath.getLong("data.phone"), is(8877445596l));

        System.out.println("jsonPath.getInt(\"data.id\") = " + jsonPath.getInt("data.id"));

    }

    @DisplayName("POST new spartan with spartan pojo")
    @Test
    public void test3() {
        Spartan spartan = new Spartan();

        spartan.setName("Harry Potter");
        spartan.setGender("Male");
        spartan.setPhone(1234567890);

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        assertThat(jsonPath.getString("data.name"), is("Harry Potter"));
        assertThat(jsonPath.getString("data.gender"), is("Male"));
        assertThat(jsonPath.getLong("data.phone"), is(1234567890l));

        System.out.println("jsonPath.getInt(\"data.id\") = " + jsonPath.getInt("data.id"));


    }

    @DisplayName("POST and GET new spartan with spartan pojo")
    @Test
    public void test4() {
        Spartan spartanPost = new Spartan();

        spartanPost.setName("Ron Wisely");
        spartanPost.setGender("Male");
        spartanPost.setPhone(1987654320);
        spartanPost.setId(500);//even if we put some id value, it didnt serialized because of the jackson annotation on the class

        Response response = given().log().body()
                .accept(ContentType.JSON) //hey api, please send me JSON RESPONSE BODY
                .and()
                .contentType(ContentType.JSON) // hey api, I am sending you JSON REQUEST BODY
                .body(spartanPost) //it will take spartan object and serialize to JSON
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success",is("A Spartan is Born!"))
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        assertThat(jsonPath.getString("data.name"), is("Ron Wisely"));
        assertThat(jsonPath.getString("data.gender"), is("Male"));
        assertThat(jsonPath.getLong("data.phone"), is(1987654320l));

        int Id = jsonPath.getInt("data.id");
        String name= jsonPath.getString("data.name");

        Response response1 = given().accept(ContentType.JSON)
                .pathParam("id", Id)
                .when()
                .log().uri()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType("application/json")
                .extract().response();
        Spartan spartan = response1.as(Spartan.class);
        System.out.println("jsonPath1List.get(Id).getName() = " + spartan.getName());

        assertThat(name,is(spartan.getName()));
    }
}
