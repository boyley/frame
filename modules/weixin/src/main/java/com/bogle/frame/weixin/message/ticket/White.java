package com.bogle.frame.weixin.message.ticket;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/14.
 */
public class White implements Serializable {

    @JsonProperty("openid")
    @JSONField(name = "openid")
    private List<String> openids;//待测试的openid

    @JsonProperty("username")
    @JSONField(name = "username")
    private List<String> usernames;//待测试的username


    public White() {
    }

    public White(List<String> openids, List<String> usernames) {
        this.openids = openids;
        this.usernames = usernames;
    }

    public List<String> getOpenids() {
        return openids;
    }

    public void setOpenids(List<String> openids) {
        this.openids = openids;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public void addUsername(String username) {
        if(this.usernames == null) {
            this.usernames = new ArrayList<>();
        }
        this.usernames.add(username);
    }

    public void addOpenid(String openid) {
        if(this.openids == null) {
            this.openids = new ArrayList<>();
        }
        this.openids.add(openid);
    }

}
