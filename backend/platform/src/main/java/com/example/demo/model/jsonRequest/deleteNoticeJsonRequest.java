package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class deleteNoticeJsonRequest {
    @JsonProperty("managerid")
    String managerId;
    @JsonProperty("id")
    String noticeId;
}
