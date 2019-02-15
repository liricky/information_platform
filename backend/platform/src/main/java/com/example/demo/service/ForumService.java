package com.example.demo.service;

import com.example.demo.model.ov.Result;

public interface ForumService {

    Result forumRecommend(String userid);

    Result forumNewReply(Integer tags);

    Result forumNewPublish(Integer tags);
}
