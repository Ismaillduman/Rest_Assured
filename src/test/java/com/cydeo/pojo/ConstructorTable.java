package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.lang.reflect.Constructor;
import java.util.List;
@Data
public class ConstructorTable {

    @JsonProperty("constructorStandings")
    @JsonPropertyOrder({
            "constructorStandings",
            "Constructors"
    })
    public String constructorStandings;
    @JsonProperty("Constructors")
    public List<Constructors> constructors;
}
