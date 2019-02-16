package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//  管理员删除公告接口
@Data
public class deleteNoticeJsonRequest {
    @JsonProperty("managerid")
    String managerId;
    @JsonProperty("id")
    String noticeId;
}
