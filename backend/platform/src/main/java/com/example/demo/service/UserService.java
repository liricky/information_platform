package com.example.demo.service;

import com.example.demo.model.jsonRequest.*;
import com.example.demo.model.ov.Result;

public interface UserService {

    Result usergetfriend(String userid);

    //  登陆接口
    Result login(loginUser user);

    //  全局根据id搜索
    Result findFriendById(String id);

    //  全局根据昵称搜索
    Result findFriendByNickname(String nickname);

    //  添加好友
    Result addFriend(addFriend addFriend);

    //  加入黑名单
    Result addToBlackList(addToBlackList blackList);

    Result userDeletePost(UserDeletePost userDeletePost);

    Result userGetPost(String userid);

    Result userDeleteComment(UserDeleteComment userDeleteComment);

    Result userGetComment(String userid);
}
