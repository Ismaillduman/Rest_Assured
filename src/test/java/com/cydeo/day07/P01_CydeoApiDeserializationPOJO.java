package com.cydeo.day07;

import com.cydeo.pojo.Address;
import com.cydeo.pojo.Student;
import com.cydeo.pojo.Students;
import com.cydeo.utilities.CydeoApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

public class P01_CydeoApiDeserializationPOJO extends CydeoApiTestBase {

@Test
public void test1(){
    JsonPath jsonPath = given().accept(ContentType.JSON)
            .pathParam("id", 2)
            .when().get("/student/{id}")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType("application/json;charset=UTF-8")
            .extract().jsonPath();

    Student student = jsonPath.getObject("students[0]", Student.class);
    System.out.println("student.getFirstName() = " + student.getFirstName());
    System.out.println("student.getLastName() = " + student.getLastName());
    System.out.println("student.getStudentId() = " + student.getStudentId());
    System.out.println("student.getCompany() = " + student.getCompany());

    System.out.println("student.getContact().getEmailAddress() = " + student.getContact().getEmailAddress());
    System.out.println("student.getCompany().getAddress() = " + student.getCompany().getAddress());
    System.out.println("student.getCompany().getAddress().getStreet() = " + student.getCompany().getAddress().getStreet());




}
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2)
                .when().get("/student/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType("application/json;charset=UTF-8")
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Students students = jsonPath.getObject("", Students.class);
        System.out.println("students = " + students);
        Student student = students.getStudents().get(0);


//if there is no path , we can use response.as method for deserialization
       // Students student1 =response.as(Students.class);


        System.out.println("student.getFirstName() = " + student.getFirstName());
        System.out.println("student.getLastName() = " + student.getLastName());
        System.out.println("student.getStudentId() = " + student.getStudentId());
        System.out.println("student.getCompany() = " + student.getCompany());

        System.out.println("student.getContact().getEmailAddress() = " + student.getContact().getEmailAddress());
        System.out.println("student.getCompany().getAddress() = " + student.getCompany().getAddress());
        System.out.println("student.getCompany().getAddress().getStreet() = " + student.getCompany().getAddress().getStreet());





    }

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2)
                .when().get("/student/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType("application/json;charset=UTF-8")
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        com.cydeo.pojo.ready.Student students = jsonPath.getObject("students[0]", com.cydeo.pojo.ready.Student.class);
        System.out.println("students.getCompany().getAddress().getZipCode() = " + students.getCompany().getAddress().getZipCode());


    }
}
