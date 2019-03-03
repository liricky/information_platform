package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class modifyPasswordByManager {
    @JsonProperty("manageid")
    String managerId;
    @JsonProperty("id")
    String userId;
    @JsonProperty("password")
    String password;
}
