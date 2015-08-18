package com.bogle.frame.weixin.defines;

import com.bogle.frame.weixin.message.Fans;

/**
 * Created by Administrator on 2015/7/8.
 */
public class OauthDefines {

    private WxCode code;
    private Fans fans;

    public OauthDefines(WxCode code) {
        this.code = code;
    }

    public OauthDefines(WxCode code, Fans fans) {
        this.code = code;
        this.fans = fans;
    }

    public WxCode getCode() {
        return code;
    }

    public void setCode(WxCode code) {
        this.code = code;
    }

    public Fans getFans() {
        return fans;
    }

    public void setFans(Fans fans) {
        this.fans = fans;
    }
}
