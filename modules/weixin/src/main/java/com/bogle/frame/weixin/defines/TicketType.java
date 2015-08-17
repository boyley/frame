package com.bogle.frame.weixin.defines;

//微信ticket类型
public enum TicketType {
    QR_CARD_TICKET,//投放卡券ticket
    QR_TICKET,//二维码ticket
    JSAPI_CARD_TICKET,//微信卡券网页ticket，通过jssdk添加到卡券包
    JSAPI_TICKET;//网页交互ticket
}