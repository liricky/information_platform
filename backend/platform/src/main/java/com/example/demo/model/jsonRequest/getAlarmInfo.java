package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class getAlarmInfo {
    @JsonProperty("id")
    String id;
    @JsonProperty("reporter_id")
    String reporterId;
    @JsonProperty("bereported_id")
    String beReportedId;
    @JsonProperty("messageid")
    String messageId;
    @JsonProperty("date")
    String date;
    @JsonProperty("type")
    String type;
    @JsonProperty("reason")
    String reason;
    @JsonProperty("content")
    String content;
}
