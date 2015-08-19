package com.bogle.frame.weixin.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2015/8/17.
 */
public class AccessToken extends WxMsg {

    @JSONField(name = "expires_in")
    private Long expiresIn;

    @JSONField(name = "refresh_token")
    private String refreshToken;

    @JSONField(name = "scope")
    private String scope;

    @JSONField(name = "unionid")
    private String unionid;

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
