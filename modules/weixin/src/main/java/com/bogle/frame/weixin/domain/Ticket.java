package com.bogle.frame.weixin.domain;

import com.bogle.frame.weixin.defines.ActionName;
import com.bogle.frame.weixin.defines.TicketType;

/**
 * Created by Administrator on 2015/8/17.
 */
public class Ticket extends com.bogle.frame.weixin.message.Ticket {

    private String id;

    private Long createTime;

    private ActionName actionName;// 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值,QR_CARD为卡券投放

    private Integer sceneId;//场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）

    private String sceneStr;//场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段

    private String tokenId;//token

    private TicketType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public ActionName getActionName() {
        return actionName;
    }

    public void setActionName(ActionName actionName) {
        this.actionName = actionName;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneStr() {
        return sceneStr;
    }

    public void setSceneStr(String sceneStr) {
        this.sceneStr = sceneStr;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }
}
