package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//  管理员添加公告
@Data
public class addNoticeJsonRequest {
    @JsonProperty("managerid")
    String managerId;
    @JsonProperty("title")
    String title;
    @JsonProperty("content")
    String content;
    @JsonProperty("type")
    String type;
}
