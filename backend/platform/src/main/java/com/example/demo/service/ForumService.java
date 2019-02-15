package com.example.demo.service;

import com.example.demo.model.ov.Result;

public interface ForumService {

    Result forumrecommend(String userid);

    Result forumnewreply(Integer tags);
}
