package com.example.demo.model.ov;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//获取所有公告接口
@Data
public class FindNoticeInfo {
    @JsonProperty("id")
    String id;
    @JsonProperty("title")
    String title;
    @JsonProperty("content")
    String content;
    @JsonProperty("date")
    String Date;
    @JsonProperty("type")
    String type;

}
