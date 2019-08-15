package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//  管理员删除帖子
@Data
public class deleteViewByManager {
    @JsonProperty("manageid")
    String managerId;
    @JsonProperty("id")
    String Id;

}
