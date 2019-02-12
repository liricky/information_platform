package com.example.demo.Model.ov;

import com.example.demo.Model.ResultCode;
import lombok.Data;

//返回格式
@Data
public class Result<Type> {
    private ResultCode code;
    private String message;
    private Type data;
}
