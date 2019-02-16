package com.example.demo.service.Impl;

import com.example.demo.dao.BlacklistMapper;
import com.example.demo.dao.FriendsMapper;
import com.example.demo.dao.UsersMapper;
import com.example.demo.model.entity.*;
import com.example.demo.model.jsonRequest.addFriend;
import com.example.demo.model.jsonRequest.addToBlackList;
import com.example.demo.model.jsonRequest.userRelationship;
import com.example.demo.model.ov.Result;
import com.example.demo.model.ov.UserGetFriend;
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

    @Resource
    private BlacklistMapper blacklistMapper;

    @Override
    public Result usergetfriend(String userid) {
        FriendsExample friendsExample=new FriendsExample();
        friendsExample.createCriteria().andUseraEqualTo(userid);
        List<Friends> friendsList= friendsMapper.selectByExample(friendsExample);
        if(friendsList.isEmpty()==true){
            return ResultTool.error("好友列表为空");
        }
        List<UserGetFriend> userGetFriendList=new LinkedList<>();
        for(Friends friends : friendsList){
            UserGetFriend userGetFriend=new UserGetFriend();
            userGetFriend.setUserid(friends.getUserb());

            Users users = usersMapper.getById(friends.getUserb());
            userGetFriend.setUsernickname(users.getName());
            userGetFriendList.add(userGetFriend);
        }
        return ResultTool.success(userGetFriendList);
    }

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

    //  添加好友
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

    //  加入黑名单
    @Override
    public Result addToBlackList(addToBlackList blackList) {
        String userIdA=blackList.getUserId();
        String userIdB=blackList.getBlacklistId();
        //  先判断是否在该用户好友列表中
        FriendsExample friendsExample=new FriendsExample();
        friendsExample.createCriteria().andUseraEqualTo(userIdA).andUserbEqualTo(userIdB);
        List<Friends> friendsList=friendsMapper.selectByExample(friendsExample);
        if(friendsList.isEmpty()==true){
            return ResultTool.error("该用户不在好友列表中");
        }
        //  从好友关系表中删除
        friendsMapper.deleteByExample(friendsExample);
        //  加入黑名单
        Blacklist black=new Blacklist();
        black.setUsera(userIdA);
        black.setUserb(userIdB);
        blacklistMapper.insert(black);
        return ResultTool.success();

    }
}
