package com.bogle.frame.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.bogle.frame.weixin.component.Http;
import com.bogle.frame.weixin.config.WeixinConfiguration;
import com.bogle.frame.weixin.defines.TicketType;
import com.bogle.frame.weixin.defines.TokenType;
import com.bogle.frame.weixin.domain.Ticket;
import com.bogle.frame.weixin.domain.Token;
import com.bogle.frame.weixin.persistence.TicketMapper;
import com.bogle.frame.weixin.persistence.TokenMapper;
import com.bogle.frame.weixin.service.IWxApi;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by Administrator on 2015/8/17.
 */
@Service
public class WxApi implements IWxApi {

    private final static Logger log = LoggerFactory.getLogger(WxApi.class);

    @Autowired
    private WeixinConfiguration weixinConfiguration;

    @Autowired
    private Http http;

    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private TicketMapper ticketMapper;

    private Token token;//基础access_token

    private Ticket jsapiTicket;
    private Ticket cardTicket;

    /**
     * 在开发者首次提交验证申请时，微信服务器将发送GET请求到填写的URL上，并且带上四个参数（signature、timestamp、nonce、echostr），开发者通过对签名（即signature）的效验，来判断此条消息的真实性。
     * <p>
     * 此后，每次开发者接收用户消息的时候，微信也都会带上前面三个参数（signature、timestamp、nonce）访问开发者设置的URL，开发者依然通过对签名的效验判断此条消息的真实性。效验方式与首次提交验证申请一致。
     * <p>
     * 参数	描述
     * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp	时间戳
     * nonce	随机数
     * echostr	随机字符串
     * 开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
     * <p>
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
    @Override
    public boolean checkSignature(String signature, String timestamp, String nonce, String echostr) {
        String[] arr = new String[]{weixinConfiguration.getTokenKey(), timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        String content = Arrays.asList(arr).toString().replaceAll(",", "").replaceAll("\\s*", "");
        content = content.substring(1, content.length() - 1);
        String tmpStr = DigestUtils.sha1Hex(content);
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        boolean flag = tmpStr != null ? tmpStr.equals(signature) : false;
        log.info("验证消息真实性 signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + ",echostr:" + echostr + ",验证结果字符串：" + tmpStr + ",返回结果：" + flag);
        return flag;
    }

    /**
     * 获取微信交互基础access_token，
     *
     * @return
     */
    @Override
    public Token getToken() {
        //基础access_token
        if (this.token == null) {
            this.token = this.tokenMapper.selectLast(TokenType.BASE_ACCESS_TOKEN);
        }
        if (token != null && token.getExpiresIn() != null) {
            Long currentTime = System.currentTimeMillis() / 1000;
            Long lastTokenCreateTime = token.getCreateTime() / 1000;
            Long gap = currentTime - lastTokenCreateTime;
            if (gap < token.getExpiresIn()) {
                return token;
            }
        }
        String tokenUrl = String.format(BASE_ACCESS_TOKEN_API_URL, weixinConfiguration.getAppId(), weixinConfiguration.getAppSecret());
        Token token = http.httpGet(tokenUrl, Token.class);
        return token;
    }

    /**
     * 网页授权access_token
     *
     * @param code
     * @return
     */
    @Override
    public Token getToken(String code) {
        //获取access_token
        String oauth2TokenUrl = String.format(OAUTH2_ACCESS_TOKEN_API_URL, weixinConfiguration.getAppId(), weixinConfiguration.getAppSecret(), code);
        //网页的access_token
        Token oauth2Token = http.httpGet(oauth2TokenUrl, Token.class);
        return oauth2Token;
    }

    /**
     * 1.微信JS接口的临时票据
     * 2.二维码投放
     *
     * @param ticketType
     * @return
     */
    @Override
    public Ticket getJSAPITicket(TicketType ticketType) {
        com.bogle.frame.weixin.domain.Ticket result = null;
        if (ticketType == TicketType.JSAPI_TICKET) {
            result = this.jsapiTicket; //微信JS接口的临时票据ticket
        } else if (ticketType == TicketType.JSAPI_CARD_TICKET) {
            result = this.cardTicket; //微信网页添加到卡券包ticket
        }
        if (result == null) {
            result = this.ticketMapper.selectLastTicket(ticketType);
        }
        if (result != null && result.getCreateTime() != null) {
            Long currentTime = System.currentTimeMillis() / 1000;
            Long lastTokenCreateTime = result.getCreateTime() / 1000;
            Long gap = currentTime - lastTokenCreateTime;
            if (gap < result.getExpiresIn()) {

                if (ticketType == TicketType.JSAPI_TICKET) {
                    this.jssapiTicket = result;
                } else if (ticketType == TicketType.JSAPI_CARD_TICKET) {
                    this.jssapiCardTicket = result;
                }
                if (log.isInfoEnabled()) {
                    log.info("返回ticket:{}", JSON.toJSONString(result));
                }
                return result;
            }
        }



        throw new RuntimeException("请检查你要获取的卡券类型");
    }
}
