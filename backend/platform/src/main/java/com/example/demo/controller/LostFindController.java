package com.example.demo.controller;

import com.example.demo.model.ov.Result;
import com.example.demo.service.LostFindService;
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
public class LostFindController {
    @Resource
    private LostFindService lostFindService;

    @GetMapping("/lostafound/board")
    public Result lostAFoundBoard(){
        return lostFindService.lostFoundBoard();
    }

    @GetMapping("/lostafound/myboard/{userid}")
    public Result lostAFoundMyBoard(HttpServletRequest httpServletRequest, @PathVariable("userid") String userid){
        String token = httpServletRequest.getHeader("Authorization");
        String userId = JwtUtil.parseJwt(token);
        if(!userId.equals(userid)){
            return ResultTool.error("登录状态无效");
        }
        return lostFindService.lostFoundMyboard(userId);
    }
}
