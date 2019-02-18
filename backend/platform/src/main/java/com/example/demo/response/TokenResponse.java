package com.example.demo.response;

import lombok.Data;

//  token返回接口
@Data
public class TokenResponse {
    private Integer identity;
    private String token;
    private String userNickname;
    private String Id;
}
