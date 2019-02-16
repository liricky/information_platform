package com.example.demo.service.Impl;

import com.example.demo.dao.*;
import com.example.demo.model.databaseResulttype.*;
import com.example.demo.model.entity.*;
import com.example.demo.model.jsonRequest.ForumChangeLike;
import com.example.demo.model.jsonRequest.ForumGetAllComment;
import com.example.demo.model.ov.*;
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

    @Resource
    private LikesMapper likesMapper;

    @Resource
    private CommentsMapper commentsMapper;

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

    //  精华帖子
    @Override
    public Result forumBest(Integer tags) {
        List<Best> bestList = viewsMapper.getBest(tags);
        List<ForumBest> forumBestList = new LinkedList<>();
        for(Best best : bestList){
            ForumBest forumBest = new ForumBest();
            forumBest.setPostid(best.getId());
            forumBest.setLabel(best.getTags());
            forumBest.setTitle(best.getTitle());
            forumBest.setAuthor(best.getPuller());
            Users users = usersMapper.getById(best.getPuller());
            forumBest.setAuthornickname(users.getName());
            forumBest.setDate(best.getTime().toString());
            forumBest.setCommentnum(viewsMapper.getCommentNum(best.getId()));
            forumBest.setLikenum(viewsMapper.getLikeNum(best.getId()));
            forumBestList.add(forumBest);
        }
        return ResultTool.success(forumBestList);
    }

    //  全部帖子
    @Override
    public Result forumAll(Integer tags) {
        List<All> allList = viewsMapper.getAll(tags);
        List<ForumAll> forumAllList = new LinkedList<>();
        for(All all : allList){
            ForumAll forumAll = new ForumAll();
            forumAll.setPostid(all.getId());
            forumAll.setLabel(all.getTags());
            forumAll.setTitle(all.getTitle());
            forumAll.setAuthor(all.getPuller());
            Users users = usersMapper.getById(all.getPuller());
            forumAll.setAuthornickname(users.getName());
            forumAll.setDate(all.getTime().toString());
            forumAll.setCommentnum(viewsMapper.getCommentNum(all.getId()));
            forumAll.setLikenum(viewsMapper.getLikeNum(all.getId()));
            forumAllList.add(forumAll);
        }
        return ResultTool.success(forumAllList);
    }

    //  获取具体帖子内容
    @Override
    public Result forumDetail(Integer postid) {
        Views views = viewsMapper.selectByPrimaryKey(postid);
        ForumDetail forumDetail = new ForumDetail();
        forumDetail.setPostid(views.getId());
        forumDetail.setLabel(views.getTags());
        forumDetail.setTitle(views.getTitle());
        forumDetail.setContent(views.getContent());
        forumDetail.setAuthor(views.getPuller());
        Users users = usersMapper.getById(views.getPuller());
        forumDetail.setAuthornickname(users.getName());
        forumDetail.setDate(views.getTime().toString());

        return ResultTool.success(forumDetail);
    }

    //  获取用户在相应帖子中的点赞状态
    @Override
    public Result forumGetLike(com.example.demo.model.jsonRequest.ForumGetLike forumGetLike) {
        LikesExample likesExample = new LikesExample();
        likesExample.createCriteria().andViewIdEqualTo(forumGetLike.getPostid()).andUserIdEqualTo(forumGetLike.getUserid());
        List<Likes> likesList = likesMapper.selectByExample(likesExample);
        ForumGetLike forumGetLike1 = new ForumGetLike();
        if(likesList != null)
            forumGetLike1.setLikestatus("true");
        else
            forumGetLike1.setLikestatus("false");
        return ResultTool.success(forumGetLike1);
    }

    //  改变用户在相应帖子中的点赞状态
    @Override
    public Result forumChangeLike(ForumChangeLike forumChangeLike) {
        LikesExample likesExample = new LikesExample();
        likesExample.createCriteria().andViewIdEqualTo(forumChangeLike.getPostid()).andUserIdEqualTo(forumChangeLike.getUserid());
        List<Likes> likesList = likesMapper.selectByExample(likesExample);
        com.example.demo.model.ov.ForumChangeLike forumChangeLike1 = new com.example.demo.model.ov.ForumChangeLike();
        if(likesList != null){
            if(forumChangeLike.getLikestatus().equals("true")) {
                likesMapper.deleteByExample(likesExample);
                forumChangeLike1.setLikestatus("false");
            } else
                return ResultTool.error("数据状态不匹配");
        } else {
            if (forumChangeLike.getLikestatus().equals("false")){
                Likes likes = new Likes();
                likes.setViewId(forumChangeLike.getPostid());
                likes.setUserId(forumChangeLike.getUserid());
                likes.setTime(new Timestamp(System.currentTimeMillis()));
                likes.setType(0);
                likesMapper.insert(likes);
                forumChangeLike1.setLikestatus("true");
            } else
                return ResultTool.error("数据状态不匹配");
        }
        return ResultTool.success(forumChangeLike1);
    }

    //  通过帖子id得到帖子热评
    @Override
    public Result forumGetHotComment(com.example.demo.model.jsonRequest.ForumGetHotComment forumGetHotComment1) {
        List<GetHotComment> getHotCommentList = commentsMapper.getHotComment(forumGetHotComment1.getPostId());
        List<ForumGetHotComment> forumGetHotCommentList = new LinkedList<>();
        for(GetHotComment getHotComment : getHotCommentList){
            ForumGetHotComment forumGetHotComment = new ForumGetHotComment();
            forumGetHotComment.setCommentid(getHotComment.getId());
            forumGetHotComment.setAuthor(getHotComment.getUser_id());
            Users users = usersMapper.getById(getHotComment.getUser_id());
            forumGetHotComment.setAuthornickname(users.getName());
            forumGetHotComment.setContent(getHotComment.getContent());
            forumGetHotComment.setDate(getHotComment.getTime().toString());
            forumGetHotComment.setLikenum(commentsMapper.getLikeNum(getHotComment.getId()));
            if(commentsMapper.getLikeStatus(forumGetHotComment1.getPostId(),forumGetHotComment1.getUserId()) == null)
                forumGetHotComment.setLikestatus("false");
            else
                forumGetHotComment.setLikestatus("true");
            forumGetHotCommentList.add(forumGetHotComment);
        }
        return ResultTool.success(forumGetHotCommentList);
    }

    @Override
    public Result forumGetHotCommentWithoutToken(Integer postid) {
        List<GetHotComment> getHotCommentList = commentsMapper.getHotComment(postid);
        List<ForumGetHotComment> forumGetHotCommentList = new LinkedList<>();
        for(GetHotComment getHotComment : getHotCommentList){
            ForumGetHotComment forumGetHotComment = new ForumGetHotComment();
            forumGetHotComment.setCommentid(getHotComment.getId());
            forumGetHotComment.setAuthor(getHotComment.getUser_id());
            Users users = usersMapper.getById(getHotComment.getUser_id());
            forumGetHotComment.setAuthornickname(users.getName());
            forumGetHotComment.setContent(getHotComment.getContent());
            forumGetHotComment.setDate(getHotComment.getTime().toString());
            forumGetHotComment.setLikenum(commentsMapper.getLikeNum(getHotComment.getId()));
            forumGetHotComment.setLikestatus("false");
            forumGetHotCommentList.add(forumGetHotComment);
        }
        return ResultTool.success(forumGetHotCommentList);
    }

    //  通过帖子id得到帖子全部评论
    @Override
    public Result forumGetAllComment(ForumGetAllComment forumGetAllComment1) {
        List<GetAllComment> getAllCommentList = commentsMapper.getAllComment(forumGetAllComment1.getPostId());
        List<com.example.demo.model.ov.ForumGetAllComment> forumGetAllCommentList = new LinkedList<>();
        for(GetAllComment getAllComment : getAllCommentList){
            com.example.demo.model.ov.ForumGetAllComment forumGetAllComment = new com.example.demo.model.ov.ForumGetAllComment();
            forumGetAllComment.setCommentid(getAllComment.getId());
            forumGetAllComment.setAuthor(getAllComment.getUser_id());
            Users users = usersMapper.getById(getAllComment.getUser_id());
            forumGetAllComment.setAuthornickname(users.getName());
            forumGetAllComment.setContent(getAllComment.getContent());
            forumGetAllComment.setDate(getAllComment.getTime().toString());
            forumGetAllComment.setLikenum(commentsMapper.getLikeNum(getAllComment.getId()));
            if(commentsMapper.getLikeStatus(forumGetAllComment1.getPostId(),forumGetAllComment1.getUserId()) == null)
                forumGetAllComment.setLikestatus("false");
            else
                forumGetAllComment.setLikestatus("true");
            forumGetAllCommentList.add(forumGetAllComment);
        }
        return ResultTool.success(forumGetAllCommentList);
    }

    @Override
    public Result forumGetAllCommentWithoutToken(Integer postid) {
        List<GetAllComment> getAllCommentList = commentsMapper.getAllComment(postid);
        List<com.example.demo.model.ov.ForumGetAllComment> forumGetAllCommentList = new LinkedList<>();
        for(GetAllComment getAllComment : getAllCommentList){
            com.example.demo.model.ov.ForumGetAllComment forumGetAllComment = new com.example.demo.model.ov.ForumGetAllComment();
            forumGetAllComment.setCommentid(getAllComment.getId());
            forumGetAllComment.setAuthor(getAllComment.getUser_id());
            Users users = usersMapper.getById(getAllComment.getUser_id());
            forumGetAllComment.setAuthornickname(users.getName());
            forumGetAllComment.setContent(getAllComment.getContent());
            forumGetAllComment.setDate(getAllComment.getTime().toString());
            forumGetAllComment.setLikenum(commentsMapper.getLikeNum(getAllComment.getId()));
            forumGetAllComment.setLikestatus("false");
            forumGetAllCommentList.add(forumGetAllComment);
        }
        return ResultTool.success(forumGetAllCommentList);
    }
}
