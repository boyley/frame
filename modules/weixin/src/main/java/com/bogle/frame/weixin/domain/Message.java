package com.bogle.frame.weixin.domain;

/**
 * Created by Administrator on 2015/8/18.
 */
public class Message extends com.bogle.frame.weixin.message.Message {
    private Long id;

    private String appId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
