package com.bogle.frame.weixin.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.bogle.frame.weixin.defines.TokenType;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/17.
 */
public class WxMsg implements Serializable {

    //用户标识
    @JSONField(name = "openid")
    private String openid;

    //错误编码
    @JSONField(name = "errcode")
    private int errcode;

    //错误消息
    @JSONField(name = "errmsg")
    private String errmsg;

    //回话token
    @JSONField(name = "access_token")
    private String accessToken;

    private TokenType tokenType;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
}
