package com.example.demo.controller;

import com.example.demo.model.jsonRequest.loginUser;
import com.example.demo.model.ov.Result;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class LoginController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody loginUser loginUser){
        return userService.login(loginUser);
    }
}
