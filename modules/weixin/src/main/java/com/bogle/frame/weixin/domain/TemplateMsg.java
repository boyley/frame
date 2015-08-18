package com.bogle.frame.weixin.domain;

/**
 * Created by Administrator on 2015/8/18.
 */
public class TemplateMsg extends com.bogle.frame.weixin.message.template.TemplateMsg {

    private Long id;

    private Long createTime;

    private String tokenId;

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

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
