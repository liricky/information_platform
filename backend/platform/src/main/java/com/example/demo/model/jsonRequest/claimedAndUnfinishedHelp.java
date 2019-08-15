package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//获取互助系统已发布任务未完成的接口
@Data
public class claimedAndUnfinishedHelp {
    @JsonProperty("missionid")
    String missionId;
    @JsonProperty("id")
    String Id;
    @JsonProperty("nickname")
    String Nickname;
    @JsonProperty("content")
    String content;
    @JsonProperty("startdate")
    String startDate;
    @JsonProperty("enddate")
    String endDate;
    @JsonProperty("phone")
    String phone;
    @JsonProperty("missionstatus")
    String missionStatus;
}
