package com.example.demo.controller;

import com.example.demo.model.ov.Result;
import com.example.demo.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
//  公告接口
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    //  获取全部公告
    @RequestMapping(value = "/announcement",method = RequestMethod.GET)
    public Result findAllNotice(HttpServletRequest httpServletRequest){
        return noticeService.findAllNotice();
    }

    //  获取最新公告
    @RequestMapping(value = "/announcement/new",method = RequestMethod.GET)
    public Result findLatestNoticeByType(HttpServletRequest httpServletRequest){
        return noticeService.findLatestNoticeByType();
    }

    //  根据日期获取公告
    @GetMapping("/announcement/{date}")
    public Result findNoticeByDate(@PathVariable("date") String date){
        return noticeService.findNoticesByDate(date);
    }

    //  根据管理员获取公告信息
    @GetMapping("/manage/announcement/{managerId}")
    public Result findNoticeByManagerId(@PathVariable("managerId") String managerId){
        return noticeService.findNoticesByManager(managerId);
    }

    //  管理员发布公告
}

