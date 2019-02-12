package com.example.demo.Model.ov;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//获取所有公告接口
@Data
public class FindNoticeInfo {
    @JsonProperty("title")
    String title;
    @JsonProperty("content")
    String content;
    @JsonProperty("date")
    String Date;
    @JsonProperty("type")
    String type;

}
