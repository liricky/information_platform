package com.example.demo.controller;

import com.example.demo.dao.UsersMapper;
import com.example.demo.model.entity.Users;
import com.example.demo.model.jsonRequest.MessageSend;
import com.example.demo.model.ov.Result;
import com.example.demo.service.MessageService;
import com.example.demo.tools.ResultTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Resource
    private UsersMapper usersMapper;

    @GetMapping("/receive/{userid}")
    public Result messageReceive(@RequestHeader(value = "Authorization") String token, @PathVariable("userid") String userid){
        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(userid)){
            return ResultTool.error("传入数据不能空！");
        }
        Users users = usersMapper.getById(userid);
        if(users.getToken().equals(token)){
            return messageService.messagereceive(userid);
        } else{
            return ResultTool.error("登录状态验证失败！");
        }
    }

    @GetMapping("/sent/{userid}")
    public Result messageSent(@RequestHeader(value = "Authorization") String token, @PathVariable("userid") String userid){
        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(userid)){
            return ResultTool.error("传入数据不能空！");
        }
        Users users = usersMapper.getById(userid);
        if(users.getToken().equals(token)){
            return messageService.messagesent(userid);
        } else{
            return ResultTool.error("登录状态验证失败！");
        }
    }

    @PostMapping("/send")
    public Result messageSend(@RequestHeader(value = "Authorization") String token, @RequestBody MessageSend messageSend){
        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(messageSend.getUserid()) || StringUtils.isEmpty(messageSend.getSendid()) || StringUtils.isEmpty(messageSend.getTitle()) || StringUtils.isEmpty(messageSend.getContent())){
            return ResultTool.error("传入数据不能空！");
        }
        Users users = usersMapper.getById(messageSend.getUserid());
        if(users.getToken().equals(token)){
            return messageService.messagesend(messageSend);
        } else{
            return ResultTool.error("登录状态验证失败！");
        }
    }
}
