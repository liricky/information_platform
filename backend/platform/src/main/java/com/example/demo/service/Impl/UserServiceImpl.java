package com.example.demo.service.Impl;

import com.example.demo.dao.FriendsMapper;
import com.example.demo.dao.UsersMapper;
import com.example.demo.model.entity.Friends;
import com.example.demo.model.entity.Users;
import com.example.demo.model.entity.UsersExample;
import com.example.demo.model.jsonRequest.addFriend;
import com.example.demo.model.jsonRequest.userRelationship;
import com.example.demo.model.ov.Result;
import com.example.demo.service.UserService;
import com.example.demo.tools.ResultTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UsersMapper usersMapper;

    @Resource
    private FriendsMapper friendsMapper;
    //  全局根据id查找人
    @Override
    public Result findFriendById(String id) {
        UsersExample usersExample=new UsersExample();
        usersExample.createCriteria().andIdEqualTo(id);
        List<Users> usersList=usersMapper.selectByExample(usersExample);
        if(usersList.isEmpty()==true){
            return ResultTool.error("该id不存在");
        }
        List<userRelationship> userRelationshipList=new LinkedList<>();
        for(Users user:usersList){
            userRelationship userRela=new userRelationship();
            userRela.setUserId(user.getId());
            userRela.setUserNickname(user.getName());
            userRelationshipList.add(userRela);
        }
        return ResultTool.success(userRelationshipList);
    }
    //  全局根据昵称查找人
    @Override
    public Result findFriendByNickname(String nickname) {
        UsersExample usersExample=new UsersExample();
        usersExample.createCriteria().andNameEqualTo(nickname);
        List<Users> usersList=usersMapper.selectByExample(usersExample);
        if(usersList.isEmpty()==true){
            return ResultTool.error("该昵称不存在");
        }
        List<userRelationship> userRelationshipList=new LinkedList<>();
        for(Users user:usersList){
            userRelationship userRela=new userRelationship();
            userRela.setUserId(user.getId());
            userRela.setUserNickname(user.getName());
            userRelationshipList.add(userRela);
        }
        return ResultTool.success(userRelationshipList);
    }

    @Override
    public Result addFriend(addFriend addFriend) {
        String userIdA=addFriend.getUserId();
        String userIdB=addFriend.getFriendId();
        //先判断要加的好友是否存在
        UsersExample usersExample=new UsersExample();
        usersExample.createCriteria().andIdEqualTo(userIdB);
        List<Users> usersList=usersMapper.selectByExample(usersExample);
        if(usersList.isEmpty()==true){
            return ResultTool.error("要加的人不存在");
        }

        Friends friend=new Friends();
        friend.setUsera(userIdA);
        friend.setUserb(userIdB);
        friendsMapper.insert(friend);
        return ResultTool.success();
    }
}
