package com.bogle.frame.weixin.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class Fans extends WxMsg {

    @JsonProperty("subscribe")
    @JSONField(name = "subscribe")
    private String subscribe;//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。

    @JsonProperty("openid")
    @JSONField(name = "openid")
    private String openid;//用户的标识，对当前公众号唯一

    @JsonProperty("nickname")
    @JSONField(name = "nickname")
    private String nickname;//用户的昵称

    @JsonProperty("sex")
    @JSONField(name = "sex")
    private Integer sex;//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知

    @JsonProperty("city")
    @JSONField(name = "city")
    private String city;//用户所在城市

    @JsonProperty("country")
    @JSONField(name = "country")
    private String country;//用户所在国家

    @JsonProperty("province")
    @JSONField(name = "province")
    private String province;//用户所在省份

    @JsonProperty("language")
    @JSONField(name = "language")
    private String language;//用户的语言，简体中文为zh_CN

    @JsonProperty("headimgurl")
    @JSONField(name = "headimgurl")
    private String headimgurl;//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。

    @JsonProperty("subscribe_time")
    @JSONField(name = "subscribe_time")
    private Timestamp subscribeTime;//用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间

    @JsonProperty("unionid")
    @JSONField(name = "unionid")
    private String unionid;//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）

    @JsonProperty("remark")
    @JSONField(name = "remark")
    private String remark;//公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注

    @JsonProperty("groupid")
    @JSONField(name = "groupid")
    private Integer groupid;//用户所在的分组ID

    @JsonProperty("privilege")
    @JSONField(name = "privilege")
    private String privilege;//用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）


    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public Timestamp getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Timestamp subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege == null ? null : privilege.trim();
    }
}