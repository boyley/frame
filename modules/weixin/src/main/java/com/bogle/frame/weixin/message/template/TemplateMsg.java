package com.bogle.frame.weixin.message.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板消息
 * Created by Administrator on 2015/7/9.
 */
public class TemplateMsg implements Serializable {

    @JsonProperty("touser")
    @JSONField(name = "touser")
    private String touser;
    @JsonProperty("template_id")
    @JSONField(name = "template_id")
    private String templateId;
    @JsonProperty("url")
    @JSONField(name = "url")
    private String url;
    @JsonProperty("topcolor")
    @JSONField(name = "topcolor")
    private String topcolor;
    @JsonProperty("data")
    @JSONField(name = "data")
    private Map<String,TemplateMsgData> data;

    public TemplateMsg() {
    }

    public TemplateMsg(String touser, String templateId, String url, String topcolor) {
        this.touser = touser;
        this.templateId = templateId;
        this.url = url;
        this.topcolor = topcolor;
    }

    public static void main(String[] args) {
        TemplateMsg templateMsg = new TemplateMsg("OPENID","ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY","http://weixin.qq.com/download","#FF0000");
        Map<String,TemplateMsgData> data = new HashMap<>();
        data.put("first",new TemplateMsgData("恭喜你购买成功！","#173177"));
        data.put("keynote1",new TemplateMsgData("巧克力","#173177"));
        data.put("keynote2",new TemplateMsgData("39.8元","#173177"));
        data.put("keynote3",new TemplateMsgData("2014年9月16日","#173177"));
        data.put("remark",new TemplateMsgData("欢迎再次购买！","#173177"));
        templateMsg.setData(data);
        String json = JSON.toJSONString(templateMsg);
        System.out.println(json);
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public Map<String, TemplateMsgData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateMsgData> data) {
        this.data = data;
    }
}
