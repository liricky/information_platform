package com.example.demo.controller;

import com.example.demo.dao.UsersMapper;
import com.example.demo.model.entity.Users;
import com.example.demo.model.jsonRequest.MessageDetail;
import com.example.demo.model.jsonRequest.MessageSend;
import com.example.demo.model.ov.Result;
import com.example.demo.service.MessageService;
import com.example.demo.tools.JwtTools;
import com.example.demo.tools.ResultTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Resource
    private UsersMapper usersMapper;

//    @GetMapping("/receive/{userid}")
//    public Result messageReceive(HttpServletRequest httpServletRequest, @PathVariable("userid") String userid){
//        String token = httpServletRequest.getHeader("Authorization");
//        if(token == "")
//            return ResultTool.error("登录状态无效！");
//        else
//            return messageService.messagereceive(userid);
//    }

    @GetMapping("/receive/{userid}")
    public Result messageReceive(HttpServletRequest httpServletRequest, @PathVariable("userid") String userid){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtTools.parseJwt(token);
        if(!userid.equals(userId))
            return ResultTool.error("登录状态无效！");
        else
            return messageService.messagereceive(userid);


    }

//    @GetMapping("/sent/{userid}")
//    public Result messageSent(HttpServletRequest httpServletRequest, @PathVariable("userid") String userid){
//        String token = httpServletRequest.getHeader("Authorization");
//        if(token == "")
//            return ResultTool.error("登录状态无效！");
//        else
//            return messageService.messagesent(userid);
//    }

    @GetMapping("/sent/{userid}")
    public Result messageSent(HttpServletRequest httpServletRequest, @PathVariable("userid") String userid){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtTools.parseJwt(token);
        if(!userid.equals(userId))
            return ResultTool.error("登录状态无效！");
        else
            return messageService.messagesent(userid);
    }

//    @PostMapping("/send")
//    public Result messageSend(HttpServletRequest httpServletRequest, @RequestBody MessageSend messageSend){
//        String token = httpServletRequest.getHeader("Authorization");
//        if(token == "")
//            return ResultTool.error("登录状态无效！");
//        else
//            return messageService.messagesend(messageSend);
//    }

    @PostMapping("/send")
    public Result messageSend(HttpServletRequest httpServletRequest, @RequestBody MessageSend messageSend){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtTools.parseJwt(token);
        if(!userId.equals(messageSend.getUserid()))
            return ResultTool.error("登录状态无效！");
        else
            return messageService.messagesend(messageSend);
    }

//    @GetMapping("/detail/{userid}/{messageid}")
//    public Result messageDetail(HttpServletRequest httpServletRequest, @PathVariable("userid") String userid, @PathVariable("messageid") Integer messageid){
//        String token = httpServletRequest.getHeader("Authorization");
//        if(token == "")
//            return ResultTool.error("登录状态无效！");
//        else {
//            MessageDetail messageDetail = new MessageDetail();
//            messageDetail.setMessageid(messageid);
//            messageDetail.setUserid(userid);
//            return messageService.messagedetail(messageDetail);
//        }
//    }

    @GetMapping("/detail/{userid}/{messageid}")
    public Result messageDetail(HttpServletRequest httpServletRequest, @PathVariable("userid") String userid, @PathVariable("messageid") Integer messageid){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtTools.parseJwt(token);
        if(!userid.equals(userId))
            return ResultTool.error("登录状态无效！");
        else {
            MessageDetail messageDetail = new MessageDetail();
            messageDetail.setMessageid(messageid);
            messageDetail.setUserid(userid);
            return messageService.messagedetail(messageDetail);
        }
    }
}
