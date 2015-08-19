package com.bogle.frame.weixin.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.bogle.frame.weixin.defines.MenuType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/7/24.
 */
public class Button implements Serializable {

    @NotNull(message = "自定义菜单名称不能为null")
    @JsonProperty("name")
    @JSONField(name = "name")
    private String name;    //菜单标题，不超过16个字节，子菜单不超过40个字节
    @JsonProperty("type")
    @JSONField(name = "type")
    private MenuType type;  //click等点击类型必须	菜单KEY值，用于消息接口推送，不超过128字节

    @JsonProperty("key")
    @JSONField(name = "key")
    private String key;//菜单KEY值，用于消息接口推送，不超过128字节

    @JsonProperty("media_id")
    @JSONField(name = "media_id")
    private String mediaId;// media_id类型和view_limited类型必须	调用新增永久素材接口返回的合法media_id
    @JsonProperty("url")
    @JSONField(name = "url")
    private String url;//view类型必须	网页链接，用户点击菜单可打开链接，不超过256字节
    @JsonProperty("sub_button")
    @JSONField(name = "sub_button")
    private List<Button> subButton;//二级菜单数组，个数应为1~5个

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Button> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<Button> subButton) {
        this.subButton = subButton;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
