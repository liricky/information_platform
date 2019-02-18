package com.example.demo.controller;

import com.example.demo.model.jsonRequest.addFriend;
import com.example.demo.model.jsonRequest.addToBlackList;
import com.example.demo.model.jsonRequest.searchPeople;
import com.example.demo.model.ov.Result;
import com.example.demo.service.UserService;
import com.example.demo.tools.ResultTool;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;
    //  搜索用户
    @RequestMapping(value = "/user/findfriend", method = RequestMethod.GET)
    public Result searchFriend(@RequestBody searchPeople people, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null) {
            return ResultTool.error("登陆状态无效");
        }
        String type = people.getType();
        String data = people.getId();
        if (type == "") {
            return ResultTool.error("类型不能为空");
        }
        int t = -1;
        try {
            t = Integer.parseInt(type);
        } catch (Exception e) {
            return ResultTool.error("类型必须是0或1");
        }
        if (t != 0 && t != 1) {
            return ResultTool.error("搜索类型错误");
        }
        if (t == 1) {
            return userService.findFriendById(data);//用户昵称搜索
        } else {
            return userService.findFriendByNickname(data);//用户姓名搜索
        }
    }

    //  添加好友
    @RequestMapping(value = "/user/addfriend",method = RequestMethod.POST)
    public Result addFriend(HttpServletRequest httpServletRequest,@RequestBody addFriend friend){
        String token = httpServletRequest.getHeader("Authorization");
        if (token == "") {
            return ResultTool.error("登陆状态无效");
        }
        return userService.addFriend(friend);
    }

    @RequestMapping(value = "/user/addblacklist",method = RequestMethod.POST)
    public Result addToBlackList(HttpServletRequest httpServletRequest,@RequestBody addToBlackList blaclList){
        String token = httpServletRequest.getHeader("Authorization");
        if (token == "") {
            return ResultTool.error("登陆状态无效");
        }
        return userService.addToBlackList(blaclList);
    }

}
