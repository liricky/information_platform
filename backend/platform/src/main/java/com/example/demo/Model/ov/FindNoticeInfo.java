package com.example.demo.Model.ov;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//获取所有公告接口
@Data
public class FindNoticeInfo {
    @JsonProperty("Title")
    String title;
    @JsonProperty("Content")
    String content;
    @JsonProperty("Date")
    String Date;
    @JsonProperty("Type")
    String type;

}
