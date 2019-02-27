package com.example.demo.controller;

import com.example.demo.model.ov.Result;
import com.example.demo.service.HelpService;
import com.example.demo.tools.JwtUtil;
import com.example.demo.tools.ResultTool;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class HelperController {
    @Resource
    private HelpService helpService;

    //  发布人获取互助系统已完成任务的接口#37
    @GetMapping(value = "/help/sendfinish/{userid}")
    public Result senderGetFinishedTask(@PathVariable("userid") String userid, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效");
        }
        if (!userid.equals(id)){
            return ResultTool.error("登陆状态无效");
        }

        System.out.println("id"+id);
        return helpService.getFinishedTaskByPusher(userid);
    }

    //  获取互助系统未认领认为
    @GetMapping(value = "/help/all")
    public Result getUnclaimedHelp(HttpServletRequest httpServletRequest){
        return helpService.getUnclaimedTasks();
    }

    //  获取互助系统已发布任务的接口#36
    @GetMapping(value = "/help/sent/{userid}")
    public Result getSentByMe(@PathVariable("userid") String userid,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效");
        }
        if (!userid.equals(id)){
            return ResultTool.error("登陆状态无效");
        }
        return helpService.getPushedByMe(id);
    }

    //  获取互助系统已认领任务接口#35
    @GetMapping(value = "/help/ongoing/{userid}")
    public Result getClaimedByMe(@PathVariable("userid") String userid,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效");
        }
        if (!userid.equals(id)){
            return ResultTool.error("登陆状态无效");
        }
        return helpService.getClaimedByMe(id);
    }

    //  获取互助系统等待认领任务#70
    @GetMapping(value = "/help/unget/{userid}")
    public Result getUnget(@PathVariable("userid") String userid,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效");
        }
        if (!userid.equals(id)){
            return ResultTool.error("登陆状态无效");
        }
        return helpService.getUnclaimedTasksByPuller(id);
    }

    //  认领人获取互助系统已完成任务的接口 #64
    @GetMapping(value = "/help/getfinish/{userid}")
    public Result receiverGetFinishedTasks(@PathVariable("userid") String userid,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效");
        }
        if (!userid.equals(id)){
            return ResultTool.error("登陆状态无效");
        }
        return helpService.getFinishedTaskByReceive(id);
    }



}
