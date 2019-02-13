package com.example.demo.service.Impl;
import com.example.demo.Model.entity.Notices;
import com.example.demo.Model.entity.NoticesExample;
import com.example.demo.Model.ov.FindNoticeInfo;
import com.example.demo.Model.ov.Result;
import com.example.demo.dao.NoticesMapper;
import com.example.demo.service.NoticeService;
import com.example.demo.tools.ResultTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticesMapper noticeMapper;


    //  查询所有的公告
    @Override
    public Result findAllNotice() {
        NoticesExample noticesExample=new NoticesExample();
        noticesExample.createCriteria().andIdIsNotNull();
        List<Notices> noticesList= noticeMapper.selectByExample(noticesExample);
        if(noticesList.isEmpty()==true){
            return ResultTool.error("公告为空");
        }
        List<FindNoticeInfo> findNoticeInfoList=new LinkedList<>();
        for(Notices notice : noticesList){
            FindNoticeInfo findNoticeInfo=new FindNoticeInfo();
            findNoticeInfo.setContent(notice.getContent());
            findNoticeInfo.setDate(notice.getTime().toString());
            findNoticeInfo.setType(notice.getType().toString());
            findNoticeInfo.setTitle(notice.getTitle());
            findNoticeInfoList.add(findNoticeInfo);
        }
        return ResultTool.success(findNoticeInfoList);
    }

    //  根据日期查询公告
    @Override
    public Result findNoticesByDate(String date) {
        String start=date+=" 00:00:00";
        String end=date+=" 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        Date start_time=new Date();
        Date end_time=new Date();
        try {
            start_time=sdf.parse(start);
            end_time=sdf.parse(end);
        }catch (Exception e){
            System.out.println("时间转换错误");
        }

        NoticesExample noticesExample=new NoticesExample();
        noticesExample.createCriteria().andTimeBetween(start_time,end_time);
        List<Notices> noticesList=noticeMapper.selectByExample(noticesExample);
        if(noticesList.isEmpty()==true){
            return ResultTool.error("该日期没有公告");

        }
        List<FindNoticeInfo> findNoticeInfoList=new LinkedList<>();
        for(Notices notice:noticesList){
            FindNoticeInfo findNoticeInfo=new FindNoticeInfo();
            findNoticeInfo.setTitle(notice.getTitle());
            findNoticeInfo.setType(notice.getType().toString());
            findNoticeInfo.setDate(notice.getTime().toString());
            findNoticeInfo.setContent(notice.getContent());
            findNoticeInfoList.add(findNoticeInfo);
        }
        return  ResultTool.success(findNoticeInfoList);
    }
}
