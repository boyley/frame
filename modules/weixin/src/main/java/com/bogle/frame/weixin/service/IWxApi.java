package com.bogle.frame.weixin.service;

import com.bogle.frame.weixin.defines.TicketType;
import com.bogle.frame.weixin.message.Ticket;
import com.bogle.frame.weixin.message.Token;

/**
 * Created by Administrator on 2015/8/17.
 */
public interface IWxApi extends IWexinApiUrl{

    /**
     * 在开发者首次提交验证申请时，微信服务器将发送GET请求到填写的URL上，
     * 并且带上四个参数（signature、timestamp、nonce、echostr），开发者通过对签名
     * （即signature）的效验，来判断此条消息的真实性。此后，每次开发者接收用户消息的时候，
     * 微信也都会带上前面三个参数（signature、timestamp、nonce）访问开发者设置的URL，
     * 开发者依然通过对签名的效验判断此条消息的真实性。效验方式与首次提交验证申请一致。
     * 加密/校验流程如下：
     * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    boolean checkSignature(String signature, String timestamp,
                           String nonce, String echostr);

    /**
     * 获取基础交互的access_token信息
     * @return
     */
    Token getToken();

    /**
     * 网页授权access_token
     * @param code
     * @return
     */
    Token getToken(String code);


    /**
     * 获取jssdk的ticket
     * 1.jsapi_ticket是公众号用于调用微信JS接口的临时票据
     * 2.微信卡券接口中使用的签名凭证api_ticket
     * @return
     */
    Ticket getJSAPITicket(TicketType ticketType);
}
