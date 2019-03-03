package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class allHelpTask {
    @JsonProperty("missionid")
    String missionId;
    @JsonProperty("authorid")
    String Id;
    @JsonProperty("authornickname")
    String Nickname;
    @JsonProperty("content")
    String content;
    @JsonProperty("startdate")
    String startDate;
    @JsonProperty("enddate")
    String endDate;

}
