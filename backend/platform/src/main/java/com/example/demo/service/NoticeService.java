package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.Model.ov.Result;
import com.example.demo.model.entity.Notices;

public interface NoticeService {
    Result findAllNotice();//查询所有公告

    Result findNoticesByDate(String date);//根据日期查询公告


}
