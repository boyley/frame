package com.bogle.frame.sms.config;

import com.bogle.frame.config.annotation.PropMap;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2015/6/30.
 */
public class SmsConfig {


    @NotNull(message = "易美软通序列号不能为空")
    @PropMap("SMS_SOFTWARE_SERIAL_NO")
    private String softwareSerialNo;//序列号

    @NotNull(message = "易美软通密码不能为空")
    @PropMap("SMS_PASSWORD")
    private String pwd;//密码（密码请手动输入）

    @NotNull(message = "易美软通特服号")
    @PropMap("SMS_SPECIAL_NO")
    private String specialNo;//特服号（方便后台查询时提供）

    @NotNull(message = "易美软通注册KEY不能为空")
    @PropMap("SMS_KEY")
    private String key;

    @NotNull(message = "易美软通是否自动注册不能为空")
    @PropMap("SMS_AUTO_REGISTER")
    private Boolean autoRegister;

    @NotNull(message = "易美软通签名不能为空")
    @PropMap("SMS_SIGN")
    private String sign;//签名

    public String getSoftwareSerialNo() {
        return softwareSerialNo;
    }

    public void setSoftwareSerialNo(String softwareSerialNo) {
        this.softwareSerialNo = softwareSerialNo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSpecialNo() {
        return specialNo;
    }

    public void setSpecialNo(String specialNo) {
        this.specialNo = specialNo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getAutoRegister() {
        return autoRegister;
    }

    public void setAutoRegister(Boolean autoRegister) {
        this.autoRegister = autoRegister;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
