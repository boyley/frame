package com.bogle.frame.sms.domain;

import com.bogle.frame.sms.variable.SendVariable;

import java.util.Date;

public class Task {
    private Long id;

    private Long createTime;

    private SendVariable sendStatus;

    private Long expireTime;

    private Long smsId;

    private String mobiles;

    private String content;

    private Long logId;

    public Task(Long smsId, SendVariable sendStatus, String mobiles, String content,Long logId) {
        this.smsId = smsId;
        this.sendStatus = sendStatus;
        this.mobiles = mobiles;
        this.content = content;
        this.logId = logId;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles == null ? null : mobiles.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public SendVariable getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(SendVariable sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }
}