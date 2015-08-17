package com.bogle.frame.weixin.exception;

/**
 * Created by Administrator on 2015/6/10.
 */
public class WeixinException extends Exception {

    private int errcode;

    public WeixinException(String message, int errcode) {
        super(message);
        this.errcode = errcode;
    }

    public int getErrcode() {
        return errcode;
    }
}
