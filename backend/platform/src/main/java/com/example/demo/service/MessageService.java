package com.example.demo.service;

import com.example.demo.Model.ov.Result;

public interface MessageService {

    Result messagereceive(String receiverid);

    Result messagesent(String sentid);

}
