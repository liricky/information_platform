package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class getViewsByManager {
    @JsonProperty("id")
    String id;
    @JsonProperty("title")
    String title;
    @JsonProperty("type")
    String type;
    @JsonProperty("addresser")
    String addresser;
    @JsonProperty("date")
    String date;
}
