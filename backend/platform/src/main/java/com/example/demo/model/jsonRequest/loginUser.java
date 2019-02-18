package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//  登陆接口
@Data
public class loginUser {
    @JsonProperty("userId")
    String userId;
    @JsonProperty("userPwd")
    String userPwd;
}
