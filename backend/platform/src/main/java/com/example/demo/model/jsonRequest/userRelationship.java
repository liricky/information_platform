package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//  全局找人接口返回信息
@Data
public class userRelationship {
    @JsonProperty("id")
    String userId;
    @JsonProperty("nickname")
    String userNickname;
}
