package com.bogle.frame.weixin.message.ticket;

import com.alibaba.fastjson.annotation.JSONField;
import com.bogle.frame.weixin.defines.ActionName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/4.
 */
public class ReqTicket implements Serializable {

    private static final int MAX_EXPIRE_SECONDS = 604800;

    @JSONField(name = "action_name")
    private ActionName actionName;//二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值

    @JSONField(name = "action_info")
    private Map<String, Map<String, Serializable>> actionInfo;//二维码详细信息


    private String cardId;
    private Integer outerId;
    private String code;
    private String openid;
    private Integer expireSeconds;
    private Boolean uniqueCode;


    public ReqTicket(ActionName actionName, Serializable scene) {
        this.actionName = actionName;
        Map<String, Serializable> scenet = new HashMap();
        if (actionName == ActionName.QR_SCENE || actionName == ActionName.QR_LIMIT_SCENE) {
            if (actionName == ActionName.QR_SCENE) {
                this.expireSeconds = MAX_EXPIRE_SECONDS;
            }
            if (scene instanceof Integer) {
                //临时二维码,永久二维码
                scenet.put("scene_id", scene);
                this.actionInfo = new HashMap<>();
                this.actionInfo.put("scene", scenet);
            } else {
                throw new RuntimeException(actionName + "不支持String类型的场景值");
            }
        } else if (actionName == ActionName.QR_LIMIT_STR_SCENE) {
            //永久二维码，scene为String
            scenet.put("scene_str", scene);
            this.actionInfo = new HashMap<>();
            this.actionInfo.put("scene", scenet);
        } else {
            throw new RuntimeException("不支持" + actionName + "ticket的创建");
        }
    }


    /**
     * 二维码投放卡券
     *
     * @param cardId        卡券ID。
     * @param outerId       领取场景值，用于领取渠道的数据统计，默认值为0，字段类型为整型，长度限制为60位数字。用户领取卡券后触发的事件推送中会带上此自定义场景值。
     * @param code          use_custom_code字段为true的卡券必须填写，非自定义code不必填写。
     * @param openid        指定领取者的openid，只有该用户能领取。bind_openid字段为true的卡券必须填写，非指定openid不必填写。
     * @param expireSeconds 指定二维码的有效时间，范围是60 ~ 1800秒。不填默认为永久有效。
     * @param uniqueCode    指定下发二维码，生成的二维码随机分配一个code，领取后不可再次扫描。填写true或false。默认false。
     */
    public ReqTicket(String cardId, Integer outerId, String code, String openid, Integer expireSeconds, Boolean uniqueCode) {
        this.actionName = ActionName.QR_CARD;
        Map<String, Serializable> card = new HashMap();
        card.put("card_id", cardId);
        card.put("code", code);
        card.put("openid", openid);
        card.put("expire_seconds", expireSeconds);
        card.put("is_unique_code", uniqueCode);
        card.put("outer_id", outerId);

        this.actionInfo = new HashMap<>();
        this.actionInfo.put("card", card);

        this.cardId = cardId;
        this.outerId = outerId;
        this.code = code;
        this.openid = openid;
        this.expireSeconds = expireSeconds;
        this.uniqueCode = uniqueCode;
    }

    public ReqTicket(String cardId) {
        this.actionName = ActionName.QR_CARD;
        Map<String, Serializable> card = new HashMap();
        card.put("card_id", cardId);
        this.cardId = cardId;
        this.actionInfo = new HashMap<>();
        this.actionInfo.put("card", card);
    }

    private void generateCard(String key, Serializable value) {
        if (this.actionInfo == null)
            this.actionInfo = new HashMap<>();
        Map<String, Serializable> card = this.actionInfo.get("card");
        if (card == null) {
            card = new HashMap<>();
        }
        card.put(key, value);
        this.actionInfo.put("card", card);
    }

    public Serializable generateScene() {
        if (this.actionName == ActionName.QR_LIMIT_SCENE) {
            Serializable secene = actionInfo.get("scene").get("scene_str");
            if (secene == null) {
                secene = actionInfo.get("scene").get("scene_id");
            }
            return secene;
        } else if (this.actionName == ActionName.QR_CARD) {
            Serializable secene = actionInfo.get("card").get("outer_id");
            return secene;
        }
        Serializable secene = actionInfo.get("scene").get("scene_id");
        return secene;
    }

    public ActionName getActionName() {
        return actionName;
    }

    public void setActionName(ActionName actionName) {
        this.actionName = actionName;
    }

    public Map<String, Map<String, Serializable>> getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(Map<String, Map<String, Serializable>> actionInfo) {
        this.actionInfo = actionInfo;
    }


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        generateCard("card_id", cardId);
        this.cardId = cardId;
    }

    public Integer getOuterId() {
        return outerId;
    }

    public void setOuterId(Integer outerId) {
        generateCard("outer_id", outerId);
        this.outerId = outerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        generateCard("code", code);
        this.code = code;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        generateCard("openid", openid);
        this.openid = openid;
    }

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Integer expireSeconds) {
        generateCard("expire_seconds", expireSeconds);
        this.expireSeconds = expireSeconds;
    }

    public Boolean getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(Boolean uniqueCode) {
        generateCard("is_unique_code", uniqueCode);
        this.uniqueCode = uniqueCode;
    }
}
