package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class getApplyInfo {
    @JsonProperty("title")
    String title;
    @JsonProperty("report")
    String report;
    @JsonProperty("date")
    String date;
    @JsonProperty("type")
    String type;
    @JsonProperty("reason")
    String reason;
}
