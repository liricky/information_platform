package com.example.demo.response;

public class CommonReturnType {
    private String status;
    private String message;
    private Object data;

    public static CommonReturnType create(Object result) {return CommonReturnType.create(result, "success",null);}

    public static CommonReturnType create(Object result, String status, String message){
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus(status);
        commonReturnType.setMessage(message);
        commonReturnType.setData(result);
        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
