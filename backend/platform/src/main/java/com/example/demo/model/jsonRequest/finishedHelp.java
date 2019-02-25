package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//  37获取已完成 70
@Data
public class finishedHelp {
    @JsonProperty("missionid")
    String missionId;
    @JsonProperty("id")
    String Id;
    @JsonProperty("nickname")
    String Nickname;
    @JsonProperty("content")
    String content;
    @JsonProperty("startdate")
    String startDate;
    @JsonProperty("enddate")
    String endDate;

}
