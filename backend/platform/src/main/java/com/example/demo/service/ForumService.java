package com.example.demo.service;

import com.example.demo.model.jsonRequest.ForumChangeLike;
import com.example.demo.model.jsonRequest.ForumGetAllComment;
import com.example.demo.model.jsonRequest.ForumGetHotComment;
import com.example.demo.model.jsonRequest.ForumGetLike;
import com.example.demo.model.ov.Result;

public interface ForumService {

    Result forumRecommend(String userid);

    Result forumNewReply(Integer tags);

    Result forumNewPublish(Integer tags);

    Result forumBest(Integer tags);

    Result forumAll(Integer tags);

    Result forumDetail(Integer postid);

    Result forumGetLike(ForumGetLike forumGetLike);

    Result forumChangeLike(ForumChangeLike forumChangeLike);

    Result forumGetHotComment(ForumGetHotComment forumGetHotComment);

    Result forumGetHotCommentWithoutToken(Integer postid);

    Result forumGetAllComment(ForumGetAllComment forumGetAllComment);

    Result forumGetAllCommentWithoutToken(Integer postid);
}
