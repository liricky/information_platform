package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//  管理员解封用户
@Data
public class freeUserByManager {
    @JsonProperty("manageid")
    String managerId;
    @JsonProperty("id")
    String id;
}
