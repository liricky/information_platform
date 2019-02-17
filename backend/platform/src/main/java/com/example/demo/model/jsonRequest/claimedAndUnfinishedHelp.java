package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//获取互助系统已发布任务的接口
@Data
public class claimedAndUnfinishedHelp {
    @JsonProperty("missionid")
    String missionId;
    @JsonProperty("acceptid")
    String acceptId;
    @JsonProperty("acceptnickname")
    String acceptNickname;
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
