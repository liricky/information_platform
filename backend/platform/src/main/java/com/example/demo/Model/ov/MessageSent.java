package com.example.demo.Model.ov;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageSent {
    @JsonProperty("messageid")
    String messageid;
    @JsonProperty("title")
    String title;
    @JsonProperty("date")
    String date;
    @JsonProperty("receiverid")
    String receiverid;
    @JsonProperty("receivernickname")
    String receivernickname;

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public String getReceivernickname() {
        return receivernickname;
    }

    public void setReceivernickname(String receivernickname) {
        this.receivernickname = receivernickname;
    }
}
