package com.bogle.frame.weixin.domain;

import com.bogle.frame.weixin.defines.TokenType;

/**
 * Created by Administrator on 2015/8/17.
 */
public class Token extends com.bogle.frame.weixin.message.Token {

    private String id;

    private TokenType type;

    private Long logId;

    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
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
