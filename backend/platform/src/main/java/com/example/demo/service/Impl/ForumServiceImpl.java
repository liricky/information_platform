package com.example.demo.service.Impl;

import com.example.demo.model.databaseResulttype.NewPublish;
import com.example.demo.model.databaseResulttype.NewReply;
import com.example.demo.model.entity.Tag_Users;
import com.example.demo.model.entity.Users;
import com.example.demo.model.entity.Views;
import com.example.demo.model.entity.ViewsExample;
import com.example.demo.model.ov.ForumNewPublish;
import com.example.demo.model.ov.ForumNewReply;
import com.example.demo.model.ov.ForumRecommend;
import com.example.demo.model.ov.Result;
import com.example.demo.dao.Tag_UsersMapper;
import com.example.demo.dao.UsersMapper;
import com.example.demo.dao.ViewsMapper;
import com.example.demo.service.ForumService;
import com.example.demo.tools.ResultTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {
    @Resource
    private ViewsMapper viewsMapper;

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private Tag_UsersMapper tag_usersMapper;

    @Override
    public Result forumRecommend(String userid) {
        List<Tag_Users> tag_usersList = tag_usersMapper.getByUser(userid);
        int sign_tag1 = 0,sign_tag2 = 1,i = 0;
        for(Tag_Users tag_users : tag_usersList){
            if(i == 0)
                sign_tag1 = tag_users.getTag();
            else if(i == 1)
                sign_tag2 = tag_users.getTag();
            else
                break;
            i++;
        }
        List<Views> viewsList = viewsMapper.getRecommend(sign_tag1);
        List<Views> viewsList1 = viewsMapper.getRecommend(sign_tag2);
        List<ForumRecommend> forumRecommendList = new LinkedList<>();
        int temp = 0;
        for(Views views : viewsList){
            ForumRecommend forumRecommend = new ForumRecommend();
            forumRecommend.setPostid(views.getId());
            forumRecommend.setLabel(views.getTags().toString());
            forumRecommend.setTitle(views.getTitle());
            forumRecommend.setAuthor(views.getPuller());
            Users users = usersMapper.getById(views.getPuller());
            forumRecommend.setAuthornickname(users.getName());
            forumRecommend.setDate(views.getTime().toString());
            int commentnum = viewsMapper.getCommentNum(views.getId());
            forumRecommend.setCommentnum(commentnum);
            int likenum = viewsMapper.getLikeNum(views.getId());
            forumRecommend.setLikenum(likenum);
            forumRecommendList.add(forumRecommend);
            if(temp == 1)
                break;
            temp++;
        }
        temp = 0;
        for(Views views : viewsList1){
            ForumRecommend forumRecommend = new ForumRecommend();
            forumRecommend.setPostid(views.getId());
            forumRecommend.setLabel(views.getTags().toString());
            forumRecommend.setTitle(views.getTitle());
            forumRecommend.setAuthor(views.getPuller());
            Users users = usersMapper.getById(views.getPuller());
            forumRecommend.setAuthornickname(users.getName());
            forumRecommend.setDate(views.getTime().toString());
            int commentnum = viewsMapper.getCommentNum(views.getId());
            forumRecommend.setCommentnum(commentnum);
            int likenum = viewsMapper.getLikeNum(views.getId());
            forumRecommend.setLikenum(likenum);
            forumRecommendList.add(forumRecommend);
            if(temp == 1)
                break;
            temp++;
        }

        return ResultTool.success(forumRecommendList);
    }

    //  最新回复的帖子
    @Override
    public Result forumNewReply(Integer tags) {
        List<NewReply> newReplyList = viewsMapper.getNewReply(tags);
        List<ForumNewReply> forumNewReplyList = new LinkedList<>();
        for(NewReply newReply : newReplyList){
            ForumNewReply forumNewReply = new ForumNewReply();
            forumNewReply.setPostid(newReply.getId());
            forumNewReply.setLabel(newReply.getTags());
            forumNewReply.setTitle(newReply.getTitle());
            forumNewReply.setAuthor(newReply.getPuller());
            Users users = usersMapper.getById(newReply.getPuller());
            forumNewReply.setAuthornickname(users.getName());
            forumNewReply.setDate(newReply.getTime().toString());
            forumNewReply.setCommentnum(viewsMapper.getCommentNum(newReply.getId()));
            forumNewReply.setLikenum(viewsMapper.getLikeNum(newReply.getId()));
            forumNewReplyList.add(forumNewReply);
        }
        return ResultTool.success(forumNewReplyList);
    }

    //  最新发布的帖子
    @Override
    public Result forumNewPublish(Integer tags) {
        List<NewPublish> newPublishList = viewsMapper.getNewPublish(tags);
        List<ForumNewPublish> forumNewPublishList = new LinkedList<>();
        for(NewPublish newPublish : newPublishList){
            ForumNewPublish forumNewPublish = new ForumNewPublish();
            forumNewPublish.setPostid(newPublish.getId());
            forumNewPublish.setLabel(newPublish.getTags());
            forumNewPublish.setTitle(newPublish.getTitle());
            forumNewPublish.setAuthor(newPublish.getPuller());
            Users users = usersMapper.getById(newPublish.getPuller());
            forumNewPublish.setAuthornickname(users.getName());
            forumNewPublish.setDate(newPublish.getTime().toString());
            forumNewPublish.setCommentnum(viewsMapper.getCommentNum(newPublish.getId()));
            forumNewPublish.setLikenum(viewsMapper.getLikeNum(newPublish.getId()));
            forumNewPublishList.add(forumNewPublish);
        }
        return ResultTool.success(forumNewPublishList);
    }
}
