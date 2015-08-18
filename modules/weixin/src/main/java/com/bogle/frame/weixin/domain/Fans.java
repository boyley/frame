package com.bogle.frame.weixin.domain;

/**
 * Created by Administrator on 2015/8/18.
 */
public class Fans extends com.bogle.frame.weixin.message.Fans {

    private String id;
    private Long createTime;
    private String tokenId;//token

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
