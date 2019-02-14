package com.example.demo.service;

import com.example.demo.model.ov.Result;

public interface MessageService {

    Result messagereceive(String receiverid);

    Result messagesent(String sentid);

    Result messagesend(String userid, String sendid, String title, String content);
}
