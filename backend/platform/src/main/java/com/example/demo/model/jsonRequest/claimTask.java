package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//  用户认领任务38
@Data
public class claimTask {
    @JsonProperty("missionid")
    String missionId;
    @JsonProperty("userid")
    String userId;
    @JsonProperty("phone")
    String phone;
}
