package com.bogle.frame.weixin.domain;

/**
 * Created by Administrator on 2015/8/18.
 */
public class Qrcode extends com.bogle.frame.weixin.message.Qrcode {

    public Qrcode() {
    }

    public Qrcode(byte[] bytes) {
        super(bytes);
    }

    private String ticketId;

    private Long createTime;

    private String suffix;

    private String name;

    private Boolean enable;

    private String sceneName;

    private String id;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
