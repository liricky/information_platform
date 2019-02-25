package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//  用户发布任务接口39
@Data
public class publishTask {
    @JsonProperty("userid")
    String userId;
    @JsonProperty("content")
    String content;
    @JsonProperty("phone")
    String phone;
    @JsonProperty("enddate")
    String endDate;
}
