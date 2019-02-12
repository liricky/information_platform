package com.example.demo.service.Impl;
import com.example.demo.Model.ov.FindNoticeInfo;
import com.example.demo.Model.ov.Result;
import com.example.demo.dao.NoticesMapper;
import com.example.demo.model.entity.Notices;
import com.example.demo.service.NoticeService;
import com.example.demo.model.entity.NoticesExample;
import com.example.demo.tools.ResultTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
