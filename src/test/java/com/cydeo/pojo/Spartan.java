package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@Data
@JsonIgnoreProperties(value = "id",allowSetters = true)

public class Spartan {
    /* {
        "id": 1,
        "name": "Meade",
        "gender": "Male",
        "phone": 3584128232
    },*/

    private int id;
    private String name;
    private String gender;
    private long phone;


}
