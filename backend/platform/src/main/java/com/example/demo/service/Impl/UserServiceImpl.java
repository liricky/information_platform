package com.example.demo.service.Impl;

import com.example.demo.Model.entity.Friends;
import com.example.demo.Model.entity.FriendsExample;
import com.example.demo.Model.entity.Users;
import com.example.demo.Model.ov.Result;
import com.example.demo.Model.ov.UserGetFriend;
import com.example.demo.dao.FriendsMapper;
import com.example.demo.dao.UsersMapper;
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

    @Override
    public Result usergetfriend(String userid) {
        FriendsExample friendsExample=new FriendsExample();
        friendsExample.createCriteria().andUseraEqualTo(userid);
        List<Friends> friendsList= friendsMapper.selectByExample(friendsExample);
        if(friendsList.isEmpty()==true){
            return ResultTool.error("公告为空");
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
}
