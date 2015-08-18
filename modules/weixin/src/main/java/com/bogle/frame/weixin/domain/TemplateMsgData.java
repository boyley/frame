package com.bogle.frame.weixin.domain;

/**
 * Created by Administrator on 2015/8/18.
 */
public class TemplateMsgData extends com.bogle.frame.weixin.message.template.TemplateMsgData {

    private String id;

    private Long templateMsgId;

    private String key;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTemplateMsgId() {
        return templateMsgId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTemplateMsgId(Long templateMsgId) {
        this.templateMsgId = templateMsgId;
    }
}
