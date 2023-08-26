package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter@Setter@ToString
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class MRData {

    private String limit;
    private String total;

    private DriverTable driverTable;

    @JsonProperty("StatusTable")
    private StatusTable statusTable;



}
