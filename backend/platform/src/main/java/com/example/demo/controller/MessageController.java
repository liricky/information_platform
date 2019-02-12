package com.example.demo.controller;


import com.example.demo.Model.jsonRequest.Message;
import com.example.demo.service.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller("message")
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private com.example.demo.service.MessageService MessageService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @ResponseBody
    @RequestMapping(value = "/receive", method = {RequestMethod.GET}, consumes = {"application/x-www-form-urlencoded"})
    public Message receive(@RequestParam(name = "token")String token,
                           @RequestParam(name = "userid")String userid){
        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(userid)){

        }


    }
}
