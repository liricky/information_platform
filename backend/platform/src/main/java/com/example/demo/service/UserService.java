package com.example.demo.service;

import com.example.demo.model.jsonRequest.addFriend;
import com.example.demo.model.ov.Result;

public interface UserService {

    //  全局根据id搜索
    Result findFriendById(String id);
    //  全局根据昵称搜索
    Result findFriendByNickname(String nickname);
    //  添加好友
    Result addFriend(addFriend addFriend);

}
