package com.example.demo.controller;

import com.example.demo.dao.UsersMapper;
import com.example.demo.model.entity.Users;
import com.example.demo.model.ov.Result;
import com.example.demo.service.ForumService;
import com.example.demo.tools.JwtTools;
import com.example.demo.tools.ResultTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @Resource
    private UsersMapper usersMapper;

//    @GetMapping("/recommend/{userid}")
//    public Result forumRecommend(HttpServletRequest httpServletRequest, @PathVariable("userid") String userid){
//        String token = httpServletRequest.getHeader("Authorization");
//        if(token == "")
//            return ResultTool.error("登录状态无效！");
//        else
//            return forumService.forumRecommend(userid);
//
//    }

    @GetMapping("/recommend/{userid}")
    public Result forumRecommend(HttpServletRequest httpServletRequest, @PathVariable("userid") String userid){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtTools.parseJwt(token);
        if(!userid.equals(userId))
            return ResultTool.error("登录状态无效！");
        else
            return forumService.forumRecommend(userid);

    }
}
