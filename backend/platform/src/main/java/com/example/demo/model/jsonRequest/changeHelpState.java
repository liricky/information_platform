package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//  用户放弃接受完成任务，40 41 42
@Data
public class changeHelpState {
    @JsonProperty("userid")
    String userId;
    @JsonProperty("missionid")
    String missionId;
}
