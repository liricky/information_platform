package com.example.demo.controller;

import com.example.demo.model.ov.Result;
import com.example.demo.service.NoticeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin

public class NoticeController {
    @Resource
    private NoticeService noticeService;

    //  获取全部公告
    @RequestMapping(value = "/announcement",method = RequestMethod.GET)
    public Result findAllNotice(HttpServletRequest httpServletRequest){
        return noticeService.findAllNotice();
    }
}

