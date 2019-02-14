package com.example.demo.controller;

import com.example.demo.dao.UsersMapper;
import com.example.demo.model.entity.Users;
import com.example.demo.model.ov.Result;
import com.example.demo.service.MessageService;
import com.example.demo.tools.ResultTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Resource
    private UsersMapper usersMapper;

    @PostMapping("/receive/{userid}")
    public Result messageReceive(@RequestHeader(value = "Authorization") String token, @PathVariable("userid") String userid){
        System.out.println(token);
        System.out.println(userid);
        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(userid)){
            return ResultTool.error("传入数据为空！");
        }

        Users users = usersMapper.getById(userid);
        if(users.getToken().equals(token)){
            return messageService.messagereceive(userid);
        } else{
            return ResultTool.error("登录状态验证失败！");
        }
    }
}
