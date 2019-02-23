package com.example.demo.controller;

import com.example.demo.model.jsonRequest.addFriend;
import com.example.demo.model.jsonRequest.addToBlackList;
import com.example.demo.model.jsonRequest.searchPeople;
import com.example.demo.model.ov.Result;
import com.example.demo.service.UserService;
import com.example.demo.tools.JwtUtil;
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
    @GetMapping(value = "/user/findfriend/{type}/{id}")
    // 搜索用户接口不需要登录也可以使用
//    public Result searchFriend(@RequestBody searchPeople people, HttpServletRequest httpServletRequest) {
    public Result searchFriend(@PathVariable("type") Integer type, @PathVariable("id") String id) {
//        String token = httpServletRequest.getHeader("Authorization");
//        if (token == null) {
//            return ResultTool.error("登陆状态无效");
//        }
        if (type != 0 && type != 1) {
            return ResultTool.error("搜索类型错误");
        }
        if (type == 1) {
            return userService.findFriendById(id);//用户昵称搜索
        } else {
            return userService.findFriendByNickname(id);//用户姓名搜索
        }
    }

    //  添加好友
//    @RequestMapping(value = "/user/addfriend",method = RequestMethod.POST)
//    public Result addFriend(HttpServletRequest httpServletRequest,@RequestBody addFriend friend){
//        String token = httpServletRequest.getHeader("Authorization");
//        if (token == "") {
//            return ResultTool.error("登陆状态无效");
//        }
//        return userService.addFriend(friend);
//    }

    @RequestMapping(value = "/user/addfriend",method = RequestMethod.POST)
    public Result addFriend(HttpServletRequest httpServletRequest,@RequestBody addFriend friend){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtUtil.parseJwt(token);
        if (!userId.equals(friend.getUserId())) {
            return ResultTool.error("登陆状态无效");
        }
        return userService.addFriend(friend);
    }

//    @RequestMapping(value = "/user/addblacklist",method = RequestMethod.POST)
//    public Result addToBlackList(HttpServletRequest httpServletRequest,@RequestBody addToBlackList blaclList){
//        String token = httpServletRequest.getHeader("Authorization");
//        if (token == "") {
//            return ResultTool.error("登陆状态无效");
//        }
//        return userService.addToBlackList(blaclList);
//    }

    @RequestMapping(value = "/user/addblacklist",method = RequestMethod.POST)
    public Result addToBlackList(HttpServletRequest httpServletRequest,@RequestBody addToBlackList blaclList){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtUtil.parseJwt(token);
        if (!userId.equals(blaclList.getUserId())) {
            return ResultTool.error("登陆状态无效");
        }
        return userService.addToBlackList(blaclList);
    }

}
