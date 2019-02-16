package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//  全局找人接口
@Data
public class userRelationship {
    @JsonProperty("userid")
    String userId;
    @JsonProperty("usernickname")
    String userNickname;
}
