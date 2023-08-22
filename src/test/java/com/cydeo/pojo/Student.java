package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Student {

    private int studentId;
    private String firstName;

    private String lastName;

    private Contact contact;
    private Company company;
}
