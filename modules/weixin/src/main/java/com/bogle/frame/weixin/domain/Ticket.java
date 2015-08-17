package com.bogle.frame.weixin.domain;

/**
 * Created by Administrator on 2015/8/17.
 */
public class Ticket extends com.bogle.frame.weixin.message.Ticket {

    private String id;

    private Long logId;

    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
