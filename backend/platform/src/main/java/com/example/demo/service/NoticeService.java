package com.example.demo.service;

import com.example.demo.Model.ov.Result;

public interface NoticeService {
    Result findAllNotice();//查询所有公告

    Result findNoticesByDate(String date);//根据日期查询公告


}
