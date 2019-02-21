package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//  获取未认领任务接口
@Data
public class unclaimHelp {
    @JsonProperty("missionid")
    String missionId;

//    public String getMissionId() {
//        return missionId;
//    }
//
//    public void setMissionId(String missionId) {
//        this.missionId = missionId;
//    }

    @JsonProperty("authorid")
    String authorId;
    @JsonProperty("authornickname")
    String authorNickname;
    @JsonProperty("content")
    String content;
    @JsonProperty("startdate")
    String startDate;
    @JsonProperty("enddate")
    String endDate;
}
