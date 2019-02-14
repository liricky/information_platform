package com.example.demo.service.Impl;

import com.example.demo.model.entity.Private_Charts;
import com.example.demo.model.entity.Private_ChartsExample;
import com.example.demo.model.entity.Users;
import com.example.demo.model.jsonRequest.MessageSend;
import com.example.demo.model.ov.MessageReceive;
import com.example.demo.model.ov.MessageSent;
import com.example.demo.model.ov.Result;
import com.example.demo.dao.Private_ChartsMapper;
import com.example.demo.dao.UsersMapper;
import com.example.demo.service.MessageService;
import com.example.demo.tools.ResultTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private Private_ChartsMapper private_chartsMapper;

    @Resource
    private UsersMapper usersMapper;

    //  查询用户收到的私信
    @Override
    public Result messagereceive(String receiverid) {
        Private_ChartsExample private_chartsExample = new Private_ChartsExample();
        private_chartsExample.createCriteria().andReceiverEqualTo(receiverid).andStateEqualTo(1);  //1 为未读内容
        Private_ChartsExample private_chartsExample1 = new Private_ChartsExample();
        private_chartsExample1.createCriteria().andReceiverEqualTo(receiverid).andStateEqualTo(0);  //0为已读内容
        List<Private_Charts> private_chartsList = private_chartsMapper.selectByExample(private_chartsExample);
        List<Private_Charts> private_chartsList1 = private_chartsMapper.selectByExample(private_chartsExample1);
        private_chartsList.addAll(private_chartsList1);
        if(private_chartsList.isEmpty() == true){
            return ResultTool.error("查询用户收到的私信为空");
        }
        List<MessageReceive> messageReceiveList = new LinkedList<>();
        for(Private_Charts private_charts : private_chartsList){
            MessageReceive messageReceive = new MessageReceive();
            messageReceive.setMessageid(private_charts.getId());
            messageReceive.setTitle(private_charts.getTitle());
            messageReceive.setDate(private_charts.getSendTime().toString());
            messageReceive.setSenderid(private_charts.getSender());

            //获取用户昵称需要从用户表中另外获取
            Users users = usersMapper.getById(private_charts.getSender());
            messageReceive.setSendernickname(users.getName());

            if(private_charts.getState() == 1)
                messageReceive.setStatus("new");
            else if(private_charts.getState() == 0)
                messageReceive.setStatus("");
            messageReceiveList.add(messageReceive);
        }
        return ResultTool.success(messageReceiveList);
    }

    //  查询用户发送的私信
    @Override
    public Result messagesent(String sentid) {
        Private_ChartsExample private_chartsExample = new Private_ChartsExample();
        private_chartsExample.createCriteria().andSenderEqualTo(sentid);
        List<Private_Charts> private_chartsList = private_chartsMapper.selectByExample(private_chartsExample);
        if(private_chartsList.isEmpty() == true){
            return ResultTool.error("查询用户发送的私信为空");
        }
        List<MessageSent> messageSentList = new LinkedList<>();
        for(Private_Charts private_charts : private_chartsList){
            MessageSent messageSent = new MessageSent();
            messageSent.setMessageid(private_charts.getId());
            messageSent.setTitle(private_charts.getTitle());
            messageSent.setDate(private_charts.getSendTime().toString());
            messageSent.setReceiverid(private_charts.getReceiver());

            //获取用户昵称需要从用户表中另外获取
            Users users = usersMapper.getById(private_charts.getId().toString());
            messageSent.setReceivernickname(users.getName());

            messageSentList.add(messageSent);
        }
        return ResultTool.success(messageSentList);
    }

    //  用户发送私信

    @Override
    public Result messagesend(MessageSend messageSend) {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Integer state = 1;
            private_chartsMapper.insertauto(messageSend.getUserid(), messageSend.getSendid(), messageSend.getTitle(), messageSend.getContent(), timestamp, state);
        } catch (Exception e) {
            return ResultTool.error(e.getMessage());
        }
        return ResultTool.success();
    }
}
