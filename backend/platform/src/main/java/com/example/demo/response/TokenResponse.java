package com.example.demo.response;

import lombok.Data;

@Data
public class TokenResponse {
    private Integer identity;
    private String token;
}