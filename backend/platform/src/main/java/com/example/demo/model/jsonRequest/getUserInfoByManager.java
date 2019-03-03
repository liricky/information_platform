package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//  管理员获取全部用户信息
@Data
public class getUserInfoByManager {
    @JsonProperty("id")
    String userId;
    @JsonProperty("name")
    String userName;
    @JsonProperty("status")
    String status;
    @JsonProperty("forbid_type")
    String banType;
    @JsonProperty("forbid_reason")
    String banReason;
    @JsonProperty("forbid_date")
    String date;
    @JsonProperty("password")
    String password;

}
