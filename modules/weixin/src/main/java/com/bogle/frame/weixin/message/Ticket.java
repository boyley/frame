package com.bogle.frame.weixin.message;

import com.alibaba.fastjson.annotation.JSONField;

public class Ticket extends WxMsg {

    @JSONField(name = "ticket")
    private String ticket;

    @JSONField(name = "expire_seconds")
    private Long expireSeconds;

    @JSONField(name = "expires_in")
    private Long expiresIn;

    @JSONField(name = "url")
    private String url;

    //use_custom_code字段为true的卡券必须填写，非自定义code不必填写。
    @JSONField(name = "code")
    private String code;
    //卡券ID
    @JSONField(name = "card_id")
    private String cardId;

    //指定下发二维码，生成的二维码随机分配一个code，领取后不可再次扫描。填写true或false。默认false。
    @JSONField(name = "is_unique_code")
    private Boolean uniqueCode;
    //领取场景值
    @JSONField(name = "outer_id")
    private Integer outerId;


    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Long getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Long expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Boolean getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(Boolean uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public Integer getOuterId() {
        return outerId;
    }

    public void setOuterId(Integer outerId) {
        this.outerId = outerId;
    }
}