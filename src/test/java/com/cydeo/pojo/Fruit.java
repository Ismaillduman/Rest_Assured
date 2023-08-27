package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor //that's provide not by @Data import manually
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fruit {
  private String name;
  private double price;
}
