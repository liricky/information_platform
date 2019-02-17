package com.example.demo.service.Impl;

import com.example.demo.dao.BlacklistMapper;
import com.example.demo.dao.FriendsMapper;
import com.example.demo.dao.UsersMapper;
import com.example.demo.model.entity.*;
import com.example.demo.model.jsonRequest.addFriend;
import com.example.demo.model.jsonRequest.addToBlackList;
import com.example.demo.model.jsonRequest.loginUser;
import com.example.demo.model.jsonRequest.userRelationship;
import com.example.demo.model.ov.Result;
import com.example.demo.response.TokenResponse;
import com.example.demo.service.UserService;
import com.example.demo.tools.AuthTool;
import com.example.demo.tools.JwtUtil;
import com.example.demo.tools.ResultTool;
import com.example.demo.tools.SecurityTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserServiceImpl implements UserService {

    private static Pattern pattern = Pattern.compile("10.*?");
    @Resource
    private UsersMapper usersMapper;

    @Resource
    private FriendsMapper friendsMapper;

    @Resource
    private BlacklistMapper blacklistMapper;

    //  登陆
    @Override
    public Result login(loginUser user) {
        if (user == null || user.getUserId() == null || "".equals(user.getUserId()) || user.getUserPwd() == null || "".equals(user.getUserPwd())) {
            return ResultTool.error("账号或密码不能为空");
        }
        // 首先验证数据库中有没有该用户
        Users existedUser = usersMapper.selectByPrimaryKey(user.getUserId());
        if (existedUser != null) {
            try {
                if (existedUser.getPassword().equals(user.getUserPwd())) {
                    //密码正确
                    TokenResponse response = new TokenResponse();
                    response.setToken(JwtUtil.createJwt(user.getUserId()));
                    response.setIdentity(existedUser.getIdentity());
                    response.setId(user.getUserId());
                    response.setUserNickname(existedUser.getName());
                    return ResultTool.success(response);
                } else if (!existedUser.getPassword().equals(SecurityTool.encodeByMd5(user.getUserPwd()))) {
                    // 如果用户在上海大学端更改了密码，我们访问接口进行验证，通过则更新数据库中用户的密码
                    if (AuthTool.getAuth(user.getUserId(), user.getUserPwd())) {
                        Users record = new Users();
                        record.setId(user.getUserId());
                        record.setPassword(user.getUserPwd());
                        usersMapper.updateByPrimaryKeySelective(record);
                        TokenResponse response = new TokenResponse();
                        response.setToken(JwtUtil.createJwt(user.getUserId()));
                        response.setIdentity(existedUser.getIdentity());
                        response.setUserNickname(existedUser.getName());
                        response.setId(existedUser.getId());
                        return ResultTool.success(response);

                    } else {
                        return ResultTool.error("账号密码错误");
                    }
                } else {
                    return ResultTool.error("您没有权限登录该系统");
                }
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                return ResultTool.error(e.getMessage());
            }
        } else {
            // 请求上海大学登陆接口查看有没有该用户，有的话该用户进入我们的数据库，没有的话返回登陆失败的信息
            if (AuthTool.getAuth(user.getUserId(), user.getUserPwd())) {
                Users systemUser = AuthTool.getInfo(user.getUserId());
                assert systemUser != null;
                systemUser.setId(user.getUserId());
//                try {
//                    systemUser.setPassword(SecurityTool.encodeByMd5(user.getUserPwd()));
//                } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//                    return ResultTool.error(e.getMessage());
//                }
                systemUser.setPassword(user.getUserPwd());
                Matcher matcher = pattern.matcher(user.getUserId());
                TokenResponse response = new TokenResponse();
                if (matcher.find()) {
                    systemUser.setIdentity(1);
                    response.setIdentity(1);
                } else {
                    systemUser.setIdentity(2);
                    response.setIdentity(2);
                }
                usersMapper.insertSelective(systemUser);
                response.setUserNickname(systemUser.getName());
                response.setId(systemUser.getId());
                response.setToken(JwtUtil.createJwt(user.getUserId()));

                return ResultTool.success(response);
            } else {
                return ResultTool.error("您不是上海大学的用户");
            }
        }
    }

    //  全局根据id查找人
    @Override
    public Result findFriendById(String id) {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andIdEqualTo(id);
        List<Users> usersList = usersMapper.selectByExample(usersExample);
        if (usersList.isEmpty() == true) {
            return ResultTool.error("该id不存在");
        }
        List<userRelationship> userRelationshipList = new LinkedList<>();
        for (Users user : usersList) {
            userRelationship userRela = new userRelationship();
            userRela.setUserId(user.getId());
            userRela.setUserNickname(user.getName());
            userRelationshipList.add(userRela);
        }
        return ResultTool.success(userRelationshipList);
    }

    //  全局根据昵称查找人
    @Override
    public Result findFriendByNickname(String nickname) {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andNameEqualTo(nickname);
        List<Users> usersList = usersMapper.selectByExample(usersExample);
        if (usersList.isEmpty() == true) {
            return ResultTool.error("该昵称不存在");
        }
        List<userRelationship> userRelationshipList = new LinkedList<>();
        for (Users user : usersList) {
            userRelationship userRela = new userRelationship();
            userRela.setUserId(user.getId());
            userRela.setUserNickname(user.getName());
            userRelationshipList.add(userRela);
        }
        return ResultTool.success(userRelationshipList);
    }

    //  添加好友
    @Override
    public Result addFriend(addFriend addFriend) {
        String userIdA = addFriend.getUserId();
        String userIdB = addFriend.getFriendId();
        //先判断要加的好友是否存在
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andIdEqualTo(userIdB);
        List<Users> usersList = usersMapper.selectByExample(usersExample);
        if (usersList.isEmpty() == true) {
            return ResultTool.error("要加的人不存在");
        }

        Friends friend = new Friends();
        friend.setUsera(userIdA);
        friend.setUserb(userIdB);
        friendsMapper.insert(friend);
        return ResultTool.success();
    }

    //  加入黑名单
    @Override
    public Result addToBlackList(addToBlackList blackList) {
        String userIdA = blackList.getUserId();
        String userIdB = blackList.getBlacklistId();
//        //先判断要加的好友是否存在
//        UsersExample usersExample = new UsersExample();
//        usersExample.createCriteria().andIdEqualTo(userIdB);
//        List<Users> usersList = usersMapper.selectByExample(usersExample);
//        if (usersList.isEmpty() == true) {
//            return ResultTool.error("要加的人不存在");
//        }
        //  先判断是否在该用户好友列表中
        FriendsExample friendsExample = new FriendsExample();
        friendsExample.createCriteria().andUseraEqualTo(userIdA).andUserbEqualTo(userIdB);
        List<Friends> friendsList = friendsMapper.selectByExample(friendsExample);
        if (friendsList.isEmpty() == true) {
            return ResultTool.error("该用户不在好友列表中");
        }
        //  从好友关系表中删除
        friendsMapper.deleteByExample(friendsExample);
        //  加入黑名单
        Blacklist black = new Blacklist();
        black.setUsera(userIdA);
        black.setUserb(userIdB);
        blacklistMapper.insert(black);
        return ResultTool.success();

    }


}
