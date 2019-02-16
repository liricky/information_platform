package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//  加入黑名单接口
@Data
public class addToBlackList {
    @JsonProperty("userid")
    String userId;
    @JsonProperty("blacklistid")
    String blacklistId;
}
