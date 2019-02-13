package com.example.demo.model.entity;

import java.util.Date;

public class Users {
    private String id;

    private String name;

    private Integer identity;

    private Boolean banstate;

    private String bantype;

    private Integer banreason;

    private Date banstart;

    private Date banend;

    private Integer coins;

    private String password;

    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Boolean getBanstate() {
        return banstate;
    }

    public void setBanstate(Boolean banstate) {
        this.banstate = banstate;
    }

    public String getBantype() {
        return bantype;
    }

    public void setBantype(String bantype) {
        this.bantype = bantype == null ? null : bantype.trim();
    }

    public Integer getBanreason() {
        return banreason;
    }

    public void setBanreason(Integer banreason) {
        this.banreason = banreason;
    }

    public Date getBanstart() {
        return banstart;
    }

    public void setBanstart(Date banstart) {
        this.banstart = banstart;
    }

    public Date getBanend() {
        return banend;
    }

    public void setBanend(Date banend) {
        this.banend = banend;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
}