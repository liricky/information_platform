package com.example.demo.model.jsonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class banUserByManager {
    @JsonProperty("manageid")
    String manageId;
    @JsonProperty("id")
    String id;
    @JsonProperty("forbid_type")
    String banType;
    @JsonProperty("forbid_reason")
    String banReason;
    @JsonProperty("forbid_date")
    String banEndDate;
}
