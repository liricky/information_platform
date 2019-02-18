package com.example.demo.service;

import com.example.demo.model.ov.Result;

public interface HelpService {
    //  获取互助系统未认领任务接口
    Result getUnclaimedTasks();

    //  获取我已发布&&被接受&&进行中任务接口
    Result getClaimedAndUnfinishedTasks(String userId);

}
