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
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Resource
    private UsersMapper usersMapper;

    @PostMapping("/receive/{userid}")
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

    @PostMapping("/sent/{userid}")
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

    @PostMapping("/send/{userid}/{sendid}/{title}/{content}")
    public Result messageSend(@RequestHeader(value = "Authorization") String token, @PathVariable("userid") String userid, @PathVariable("sendid") String sendid, @PathVariable("title") String title, @PathVariable("content") String content){
        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(userid) || StringUtils.isEmpty(sendid) || StringUtils.isEmpty(title) || StringUtils.isEmpty(content)){
            return ResultTool.error("传入数据不能空！");
        }
        Users users = usersMapper.getById(userid);
        if(users.getToken().equals(token)){
            MessageSend messageSend = new MessageSend();
            messageSend.setUserid(userid);
            messageSend.setSendid(sendid);
            messageSend.setTitle(title);
            messageSend.setContent(content);
            return messageService.messagesend(messageSend);
        } else{
            return ResultTool.error("登录状态验证失败！");
        }
    }
}
