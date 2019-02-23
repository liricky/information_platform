package com.example.demo.service.Impl;

import com.example.demo.controller.UserController;
import com.example.demo.dao.*;
import com.example.demo.model.entity.*;
import com.example.demo.model.jsonRequest.*;
import com.example.demo.model.ov.Result;
import com.example.demo.model.ov.UserGetComment;
import com.example.demo.model.ov.UserGetFriend;
import com.example.demo.model.ov.UserGetPost;
import com.example.demo.response.TokenResponse;
import com.example.demo.service.UserService;
import com.example.demo.tools.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.View;
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

    @Resource
    private Tag_UsersMapper tag_usersMapper;

    @Resource
    private ViewsMapper viewsMapper;

    @Resource
    private CommentsMapper commentsMapper;

    @Override
    public Result usergetfriend(String userid) {
        FriendsExample friendsExample=new FriendsExample();
        friendsExample.createCriteria().andUseraEqualTo(userid);
        List<Friends> friendsList= friendsMapper.selectByExample(friendsExample);
        if(friendsList.isEmpty()){
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

                //  加入用户关系表
                for(int i=1;i<=4;i++){
                    Tag_Users tag_users=new Tag_Users();
                    tag_users.setUser(user.getUserId());
                    tag_users.setTag(i);
                    tag_users.setExtent(0);
                    tag_usersMapper.insert(tag_users);
                }

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

    //  删除用户自己所发帖的接口
    @Override
    public Result userDeletePost(UserDeletePost userDeletePost) {
        ViewsExample viewsExample = new ViewsExample();
        viewsExample.createCriteria().andPullerEqualTo(userDeletePost.getUserid()).andIdEqualTo(userDeletePost.getPostid());
        List<Views> viewsList = viewsMapper.selectByExample(viewsExample);
        if(viewsList.isEmpty()){
            return ResultTool.error("要删除的帖子不存在！");
        } else{
            try {
                viewsMapper.deleteByPrimaryKey(userDeletePost.getPostid());
            } catch (Exception e){
                return ResultTool.error("删除帖子出错！");
            }
            return ResultTool.success();
        }
    }

    //  获取用户历史发帖的接口
    @Override
    public Result userGetPost(String userid) {
        ViewsExample viewsExample = new ViewsExample();
        viewsExample.createCriteria().andPullerEqualTo(userid);
        List<Views> viewsList = viewsMapper.selectByExample(viewsExample);
        if(viewsList.isEmpty()){
            return ResultTool.error("无历史发帖！");
        } else{
            List<UserGetPost> userGetPostList = new LinkedList<>();
            for(Views views : viewsList) {
                UserGetPost userGetPost = new UserGetPost();
                userGetPost.setPostid(views.getId());
                if(views.getTags() == 1)
                    userGetPost.setLabel("体育");
                else if(views.getTags() == 2)
                    userGetPost.setLabel("学术");
                else if(views.getTags() == 3)
                    userGetPost.setLabel("音乐");
                else if(views.getTags() == 4)
                    userGetPost.setLabel("游戏");
                userGetPost.setTitle(views.getTitle());
                userGetPost.setAuthor(views.getPuller());
                //获取用户昵称需要从用户表中另外获取
                Users users = usersMapper.getById(views.getPuller());
                userGetPost.setAuthornickname(users.getName());
                userGetPost.setDate(views.getTime().toString());
                if(viewsMapper.getCommentNum(views.getId()) instanceof Integer)
                    userGetPost.setCommentnum(viewsMapper.getCommentNum(views.getId()));
                else
                    userGetPost.setCommentnum(0);
                if(viewsMapper.getLikeNum(views.getId()) instanceof Integer)
                    userGetPost.setLikenum(viewsMapper.getLikeNum(views.getId()));
                else
                    userGetPost.setLikenum(0);
                userGetPostList.add(userGetPost);
            }
            return ResultTool.success(userGetPostList);
        }
    }

    //  删除用户自己回帖的接口
    @Override
    public Result userDeleteComment(UserDeleteComment userDeleteComment) {
        CommentsExample commentsExample = new CommentsExample();
        commentsExample.createCriteria().andUserIdEqualTo(userDeleteComment.getUserid()).andIdEqualTo(userDeleteComment.getCommentid());
        List<Comments> commentsList = commentsMapper.selectByExample(commentsExample);
        if(commentsList.isEmpty()){
            return ResultTool.error("要删除的回帖不存在！");
        } else{
            try {
                commentsMapper.deleteByPrimaryKey(userDeleteComment.getCommentid());
            } catch (Exception e){
                return ResultTool.error("删除回帖出错！");
            }
            return ResultTool.success();
        }
    }

    //  获取用户历史回帖记录的接口
    @Override
    public Result userGetComment(String userid) {
        CommentsExample commentsExample = new CommentsExample();
        commentsExample.createCriteria().andUserIdEqualTo(userid);
        List<Comments> commentsList = commentsMapper.selectByExample(commentsExample);
        if(commentsList.isEmpty()){
            return ResultTool.error("无历史回帖！");
        } else{
            List<UserGetComment> userGetCommentList = new LinkedList<>();
            for(Comments comments : commentsList) {
                UserGetComment userGetComment = new UserGetComment();
                userGetComment.setId(comments.getId());
                userGetComment.setPostid(comments.getViewId());
                Views views = viewsMapper.selectByPrimaryKey(comments.getViewId());
                userGetComment.setTitle(views.getTitle());
                userGetComment.setContent(comments.getContent());
                userGetComment.setAuthor(comments.getUserId());
                //获取用户昵称需要从用户表中另外获取
                Users users = usersMapper.getById(comments.getUserId());
                userGetComment.setAuthornickname(users.getName());
                userGetComment.setDate(comments.getTime().toString());
                if(commentsMapper.getLikeNum(comments.getId()) instanceof Integer)
                    userGetComment.setLikenum(commentsMapper.getLikeNum(comments.getId()));
                else
                    userGetComment.setLikenum(0);
                userGetCommentList.add(userGetComment);
            }
            return ResultTool.success(userGetCommentList);
        }
    }

    //  修改用户密码的接口
    @Override
    public Result editPwd(EditPwd editPwd) {
        Users users = usersMapper.selectByPrimaryKey(editPwd.getUserId());
        if(users == null){
            return ResultTool.error("用户无效");
        } else{
            if(users.getPassword().equals(editPwd.getUserOldPwd())){
                Users users1 = new Users();
                users1.setId(editPwd.getUserId());
                users1.setPassword(editPwd.getUserNewPwd());
                try {
                    usersMapper.updateByPrimaryKeySelective(users1);
                } catch(Exception e){
                    return ResultTool.error("修改发生错误");
                }
                return ResultTool.success();
            } else{
                return ResultTool.error("原始密码不正确，如果忘记原密码请联系管理员！");
            }
        }

    }
}
