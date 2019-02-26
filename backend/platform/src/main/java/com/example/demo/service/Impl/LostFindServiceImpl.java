package com.example.demo.service.Impl;

import com.example.demo.dao.LostlistMapper;
import com.example.demo.dao.NoticesMapper;
import com.example.demo.dao.UsersMapper;
import com.example.demo.model.entity.Lostlist;
import com.example.demo.model.entity.Notices;
import com.example.demo.model.entity.NoticesExample;
import com.example.demo.model.entity.Users;
import com.example.demo.model.ov.LostAFoundBoard;
import com.example.demo.model.ov.LostAFoundMyBoard;
import com.example.demo.model.ov.Result;
import com.example.demo.service.LostFindService;
import com.example.demo.tools.ResultTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class LostFindServiceImpl implements LostFindService {
    @Resource
    private LostlistMapper lostlistMapper;

    @Resource
    private NoticesMapper noticesMapper;

    @Resource
    private UsersMapper usersMapper;

    //  获取失物招领信息
    @Override
    public Result lostFoundBoard() {
        NoticesExample noticesExample = new NoticesExample();
        noticesExample.createCriteria().andTypeEqualTo(3);
        List<Notices> noticesList = noticesMapper.selectByExample(noticesExample);
        List<LostAFoundBoard> lostAFoundBoardList = new LinkedList<>();
        for (Notices notices : noticesList){
            LostAFoundBoard lostAFoundBoard = new LostAFoundBoard();
            lostAFoundBoard.setId(notices.getId());
            lostAFoundBoard.setTitle(notices.getTitle());
            lostAFoundBoard.setContent(notices.getContent());
            Lostlist lostlist = lostlistMapper.selectByPrimaryKey(notices.getId());
            lostAFoundBoard.setConnect(lostlist.getContent());
            lostAFoundBoard.setUserid(notices.getPuller());
            Users users = usersMapper.getById(notices.getPuller());
            lostAFoundBoard.setAuthor(users.getName());
            lostAFoundBoard.setDate(notices.getTime().toString());
            lostAFoundBoardList.add(lostAFoundBoard);
        }
        return ResultTool.success(lostAFoundBoardList);
    }

    //  获取用户自身发布的失物招领信息
    @Override
    public Result lostFoundMyboard(String userid) {
        NoticesExample noticesExample = new NoticesExample();
        noticesExample.createCriteria().andTypeEqualTo(3).andPullerEqualTo(userid);
        List<Notices> noticesList = noticesMapper.selectByExample(noticesExample);
        List<LostAFoundMyBoard> lostAFoundMyBoardList = new LinkedList<>();
        for(Notices notices : noticesList){
            LostAFoundMyBoard lostAFoundMyBoard = new LostAFoundMyBoard();
            lostAFoundMyBoard.setId(notices.getId());
            lostAFoundMyBoard.setTitle(notices.getTitle());
            lostAFoundMyBoard.setContent(notices.getContent());
            Lostlist lostlist = lostlistMapper.selectByPrimaryKey(notices.getId());
            lostAFoundMyBoard.setConnect(lostlist.getContent());
            lostAFoundMyBoard.setDate(notices.getTime().toString());
            lostAFoundMyBoardList.add(lostAFoundMyBoard);
        }
        return ResultTool.success(lostAFoundMyBoardList);
    }
}
