package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//  添加好友接口
@Data
public class addFriend {
    @JsonProperty("userid")
    String userId;
    @JsonProperty("friendid")
    String friendId;
}
