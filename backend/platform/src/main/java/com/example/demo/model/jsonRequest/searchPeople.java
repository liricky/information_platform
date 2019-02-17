package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class searchPeople {
    @JsonProperty("type")
    String type;
    @JsonProperty("id")
    String id;
}
