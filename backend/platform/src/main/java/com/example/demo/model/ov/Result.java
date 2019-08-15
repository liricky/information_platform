package com.example.demo.model.ov;

import com.example.demo.model.ResultCode;
import lombok.Data;

//返回格式
@Data
public class Result<Type> {
    private ResultCode status;
    private String message;
    private Type data;
}
