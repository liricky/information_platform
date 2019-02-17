package com.example.demo.service.Impl;

import com.example.demo.dao.HelpMapper;
import com.example.demo.dao.UsersMapper;
import com.example.demo.model.entity.Help;
import com.example.demo.model.entity.HelpExample;
import com.example.demo.model.entity.Users;
import com.example.demo.model.entity.UsersExample;
import com.example.demo.model.jsonRequest.claimedAndUnfinishedHelp;
import com.example.demo.model.jsonRequest.unclaimHelp;
import com.example.demo.model.ov.Result;
import com.example.demo.service.HelpService;
import com.example.demo.tools.ResultTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Service
public class HelpServiceImpl implements HelpService {

    @Resource
    private HelpMapper helpMapper;

    @Resource
    private UsersMapper usersMapper;
    //  状态0=未认领 1=已经认领但是未完成 2=一方完成 3=两方完成
    //  获取互助系统未认领任务接口
    @Override
    public Result getUnclaimedTasks() {
        Date time;
        String time_s = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
        time = Timestamp.valueOf(time_s);
        HelpExample helpExample=new HelpExample();
        //未认领&&未过期任务
        helpExample.createCriteria().andStateEqualTo(0).andStartTimeLessThan(time).andEndTimeGreaterThan(time);
        List<Help> helpList=helpMapper.selectByExample(helpExample);
        if(helpList.isEmpty()==true){
            return ResultTool.error("不存在未认领任务");
        }
        List<unclaimHelp> unclaimHelpList=new LinkedList<>();
        for(Help help:helpList){
            unclaimHelp unclaimHelpTask=new unclaimHelp();
            unclaimHelpTask.setMissionId(help.getId().toString());
            unclaimHelpTask.setAuthorId(help.getPuller());
            unclaimHelpTask.setAuthorNickname(getUsername(help.getPuller()));
            unclaimHelpTask.setContent(help.getContent());
            unclaimHelpTask.setStartDate(help.getStartTime().toString());
            unclaimHelpTask.setEndDate(help.getEndTime().toString());
            unclaimHelpList.add(unclaimHelpTask);
        }
        return ResultTool.success(unclaimHelpList);
    }
    //  根据id获取用户姓名
    private String getUsername(String id){
        Users user= usersMapper.selectByPrimaryKey(id);
        return user.getName();
    }

    @Override
    //  获取我已发布未截止且有人认领的且未完成务接口
    public Result getClaimedAndUnfinishedTasks(String userId){
        Date time;
        String time_s = DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date());
        time = Timestamp.valueOf(time_s);
        HelpExample helpExample=new HelpExample();
        helpExample.createCriteria().andPullerEqualTo(userId).andStartTimeLessThan(time).andEndTimeGreaterThan(time).andStateNotEqualTo(3);
        List<Help> helpList=helpMapper.selectByExample(helpExample);
        if(helpList.isEmpty()==true){
            return ResultTool.error("不存在你发布&&被认领的&&未完成任务");
        }
        List<claimedAndUnfinishedHelp> claimedAndUnfinishedHelpList=new LinkedList<>();
        for(Help help:helpList){
            claimedAndUnfinishedHelp item=new claimedAndUnfinishedHelp();
            item.setMissionId(help.getId().toString());
            item.setAcceptId(help.getReceiver());
            item.setAcceptNickname(getUsername(help.getReceiver()));
            item.setContent(help.getContent());
            item.setStartDate(help.getStartTime().toString());
            item.setEndDate(help.getEndTime().toString());
            item.setPhone(help.getReceiverPhone());
            item.setMissionStatus("进行中");
            claimedAndUnfinishedHelpList.add(item);
        }
        return ResultTool.success(claimedAndUnfinishedHelpList);
    }

    
}
