package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class deleteNoticeJsonRequest {
    @JsonProperty("managerid")
    String managerId;
    @JsonProperty("id")
    String noticeId;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }
}
