package com.example.demo.model.entity;

import java.util.Date;

public class Alarm {
    private Integer id;

    private String alarmingUser;

    private String alarmedUser;

    private Integer alarmType;

    private Date time;

    private String reason;

    private Integer viewType;

    private Integer commentId;

    private Integer viewId;

    private Integer taskId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlarmingUser() {
        return alarmingUser;
    }

    public void setAlarmingUser(String alarmingUser) {
        this.alarmingUser = alarmingUser == null ? null : alarmingUser.trim();
    }

    public String getAlarmedUser() {
        return alarmedUser;
    }

    public void setAlarmedUser(String alarmedUser) {
        this.alarmedUser = alarmedUser == null ? null : alarmedUser.trim();
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getViewType() {
        return viewType;
    }

    public void setViewType(Integer viewType) {
        this.viewType = viewType;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}