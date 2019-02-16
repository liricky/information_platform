package com.example.demo.model.ov;

import com.example.demo.model.ResultCode;
import lombok.Data;

//返回格式
@Data
public class Result<Type> {
    private ResultCode code;
    private String message;
    private Type data;

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getData() {
        return data;
    }

    public void setData(Type data) {
        this.data = data;
    }
}
