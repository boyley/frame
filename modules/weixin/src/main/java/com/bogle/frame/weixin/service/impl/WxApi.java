package com.bogle.frame.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bogle.frame.weixin.component.Http;
import com.bogle.frame.weixin.config.WeixinConfiguration;
import com.bogle.frame.weixin.defines.*;
import com.bogle.frame.weixin.domain.Fans;
import com.bogle.frame.weixin.domain.Qrcode;
import com.bogle.frame.weixin.domain.Ticket;
import com.bogle.frame.weixin.domain.Token;
import com.bogle.frame.weixin.event.MessageEvent;
import com.bogle.frame.weixin.exception.WeixinException;
import com.bogle.frame.weixin.message.Button;
import com.bogle.frame.weixin.message.Message;
import com.bogle.frame.weixin.message.Template;
import com.bogle.frame.weixin.message.WxMsg;
import com.bogle.frame.weixin.message.template.TemplateMsg;
import com.bogle.frame.weixin.message.ticket.ReqTicket;
import com.bogle.frame.weixin.persistence.TicketMapper;
import com.bogle.frame.weixin.persistence.TokenMapper;
import com.bogle.frame.weixin.service.IWxApi;
import com.bogle.frame.weixin.service.MessageHandler;
import com.thoughtworks.xstream.XStream;
import org.apache.catalina.util.URLEncoder;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Administrator on 2015/8/17.
 */
@Service
public class WxApi implements IWxApi {

    private final static Logger log = LoggerFactory.getLogger(WxApi.class);

    private URLEncoder encoder = new URLEncoder();

    private static final XStream xStream = new XStream();

    static {
        xStream.autodetectAnnotations(true);
        xStream.processAnnotations(Message.class);
    }

    @Autowired
    private WeixinConfiguration weixinConfiguration;

    @Autowired
    private Http http;

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired(required = false)
    private MessageHandler messageHandler;

    @Autowired
    private ApplicationEventPublisher publisher;

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
     * JS-SDK使用权限签名算法
     *
     * @param url
     * @return
     * @throws WeixinException
     */
    @Override
    public Map<String, String> signature(String url) {
        Map<String, String> ret = new HashMap<>();
        Ticket ticket = null;
        try {
            ticket = this.getTicket(TicketType.JSAPI_TICKET);
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        String nonceStr = this.createNonceStr();
        String timestamp = this.createTimestamp();
        String sign = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";
        String signature = String.format(sign, ticket.getTicket(), nonceStr, timestamp, url);
        signature = DigestUtils.sha1Hex(signature);
        ret.put("url", url);
        ret.put("jsapi_ticket", ticket.getTicket());
        ret.put("nonceStr", nonceStr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appId", weixinConfiguration.getAppId());
        return ret;
    }

    /**
     * 获取微信交互基础access_token，
     *
     * @return
     */
    @Override
    public Token getToken() throws WeixinException {
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
        String url = String.format(BASE_ACCESS_TOKEN_API_URL, weixinConfiguration.getAppId(), weixinConfiguration.getAppSecret());
        Token token = http.httpGet(url, Token.class);
        return token;
    }

    /**
     * 网页授权access_token
     *
     * @param code
     * @return
     */
    @Override
    public Token getToken(String code) throws WeixinException {
        //获取access_token
        String url = String.format(OAUTH2_ACCESS_TOKEN_API_URL, weixinConfiguration.getAppId(), weixinConfiguration.getAppSecret(), code);
        //网页的access_token
        Token oauth2Token = http.httpGet(url, Token.class);
        return oauth2Token;
    }

    /**
     * 1.微信JS接口的临时票据
     * 2.添加卡券到卡券包ticket
     *
     * @param ticketType
     * @return
     */
    @Override
    public Ticket getTicket(TicketType ticketType) throws WeixinException {
        Ticket result = null;
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
                    this.jsapiTicket = result;
                } else if (ticketType == TicketType.JSAPI_CARD_TICKET) {
                    this.cardTicket = result;
                }
                if (log.isInfoEnabled()) {
                    log.info("返回ticket:{}", JSON.toJSONString(result));
                }
                return result;
            }
        }
        String url;
        if (ticketType == TicketType.JSAPI_TICKET) {
            Token token = this.getToken();
            url = String.format(JSAPI_TICKET_API_URL, token.getAccessToken()) + "&" + TOKEN_ID + token.getId();
            this.jsapiTicket = http.httpGet(url, Ticket.class);
            return this.jsapiTicket;
        } else if (ticketType == TicketType.JSAPI_CARD_TICKET) {
            Token token = this.getToken();
            url = String.format(JSAPI_CARD_TICKET_URL_API_URL, token.getAccessToken()) + "&" + TOKEN_ID + token.getId();
            this.cardTicket = http.httpGet(url, Ticket.class);
            return this.cardTicket;
        }
        throw new RuntimeException("请检查你要获取的卡券类型");
    }


    /**
     * ticket获取
     * 1. 投放卡券二维码ticket
     * 2. 生成带参数的二维码ticket
     *
     * @param reqTicket
     * @return
     */
    @Override
    public Ticket getTicket(ReqTicket reqTicket) throws WeixinException {
        ActionName actionName = reqTicket.getActionName();
        String url = null;
        if (actionName == ActionName.QR_CARD) {
            //投放卡券二维码ticket
            url = CARD_TICKET_URL_API_URL;
        } else if (actionName == ActionName.QR_SCENE || actionName == ActionName.QR_LIMIT_SCENE || actionName == ActionName.QR_LIMIT_STR_SCENE) {
            //生成带参数的二维码ticket ,QR_SCENE为临时;生成带参数的二维码ticket ,QR_LIMIT_SCENE永久;生成带参数的二维码ticket ,QR_LIMIT_STR_SCENE永久
            url = QRCODE_TICKET_URL_API_URL;
        }
        if (url != null) {
            Token token = this.getToken();
            url = String.format(url, token.getAccessToken()) + "&" + TOKEN_ID + token.getId();
            return http.httpPost(url, reqTicket, Ticket.class);
        }
        throw new RuntimeException("获取ticket的action_name有误，请检查");
    }

    /**
     * 根据ticket获取二维码图片
     *
     * @param reqTicket
     * @return
     */
    @Override
    public Qrcode getQrcode(ReqTicket reqTicket) throws WeixinException {
        Ticket ticket = this.getTicket(reqTicket);
        String ticketString = encoder.encode(ticket.getTicket());
        String url = String.format(QRCODE_URL_API_URL, ticketString);
        byte[] bytes = http.httpGet(url);
        return new Qrcode(bytes);
    }


    /**
     * 网页授权获取用户基本信息
     *
     * @param code
     * @return
     */
    @Override
    public OauthDefines oauth2(String code) {
        Token token = null;
        try {
            token = this.getToken(code);
            //拉取用户信息(需scope为 snsapi_userinfo)
            String url = String.format(SNSAPI_USERINFO_API_URL, token.getAccessToken(), token.getOpenid());
            Fans userInfo = this.getFans(url);
            OauthDefines oauthDefines = new OauthDefines(WxCode.SUCCESS, userInfo);
            return oauthDefines;
        } catch (Exception e) {
            if (e instanceof WeixinException) {
                WeixinException ex = (WeixinException) e;
                OauthDefines oauthDefines = new OauthDefines(ex.getErrcode());
                if (log.isErrorEnabled()) {
                    log.info("网页授权错误:{}", JSON.toJSONString(oauthDefines));
                }
                return oauthDefines;
            } else
                e.printStackTrace();
        }
        OauthDefines oauthDefines = new OauthDefines(WxCode.ERROR);
        return oauthDefines;
    }

    @Override
    public String generateOauth2Url(String url, String state) {
        url = encoder.encode(url);
        return String.format(SNSAPI,weixinConfiguration.getAppId(),url,OauthType.snsapi_userinfo,state);
    }

    @Override
    public Fans getFans(String url) throws WeixinException {
        return http.httpGet(url, Fans.class);
    }

    @Override
    public Template send(TemplateMsg templateMsg) throws WeixinException {
        Token token = this.getToken();
        String url = String.format(SEND_TEMPLATE_MSG_API_URL, token.getAccessToken()) + "&" + TOKEN_ID + token.getId();
        return http.httpPost(url, templateMsg, Template.class);
    }

    /**
     * 自定义菜单
     * 1、自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
     * 2、一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。
     * 3、创建自定义菜单后，由于微信客户端缓存，需要24小时微信客户端才会展现出来。测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
     *
     * @param buttons   自定义菜单集合
     * @return
     */
    @Override
    public WxMsg customMenu(List<Button> buttons) throws WeixinException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("button",buttons);
        Token token = this.getToken();
        String url = String.format(CUSTOM_MENU_API_URL,token.getAccessToken());
        return this.http.httpPost(url,jsonObject,WxMsg.class);
    }

    /**
     * 接收处理消息
     *
     * @param reqMsg
     * @return
     */
    @Override
    public Serializable process(String reqMsg) {
        Message message = (Message) xStream.fromXML(reqMsg);
        Serializable result = process(message);
        return result;
    }

    /**
     * 处理消息
     *
     * @param message
     * @return
     */
    private Serializable process(Message message) {
        publisher.publishEvent(new MessageEvent(this, message, this.weixinConfiguration.getAppId()));
        Message respMsg = null;
        if (message.getMsgType().equals(MsgType.MESSAGETYPE_TEXT.toString())) {
            //图文消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_IMAGE.toString())) {
            //图片消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_VOICE.toString())) {
            //语音消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_VIDEO.toString())) {
            //视频消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_SHORTVIDEO.toString())) {
            //小视频消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_LOCATION.toString())) {
            //地理位置消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_MUSIC.toString())) {
            //音乐消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_LINK.toString())) {
            //链接消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_EVENT.toString())) {
            //事件推送
            if (message.getEvent() != null) {
                String event = message.getEvent();
                String eventKey = message.getEventKey();
                if ((event.equals(Event.SCAN.toString())) || (eventKey != null && eventKey.startsWith(Event.KEY_QRSCENE.toString()) && event.equals(Event.SUBSCRIBE.toString()))) {
                    /**用户扫描事件*/
                } else if (event.equals(Event.SUBSCRIBE.toString())) {
                    /**用户关注事件*/
                } else if (event.equals(Event.UNSUBSCRIBE.toString())) {
                    /**用户取消关注事件*/
                } else if (event.equals(Event.LOCATION.toString())) {
                    /**上报地理位置事件*/
                } else if (event.equals(Event.CLICK)) {
                    /**用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。*/
                } else if (event.equals(Event.VIEW.toString())) {
                    /**点击菜单跳转链接时的事件推送*/
                } else if (event.equals(Event.SCANCODE_PUSH.toString())) {
                    /**扫码推事件的事件推送*/
                } else if (event.equals(Event.SCANCODE_WAITMSG.toString())) {
                    /**扫码推事件且弹出“消息接收中”提示框的事件推送*/
                } else if (event.equals(Event.PIC_SYSPHOTO.toString())) {
                    /**弹出系统拍照发图的事件推送*/
                } else if (event.equals(Event.PIC_PHOTO_OR_ALBUM.toString())) {
                    /**弹出拍照或者相册发图的事件推送*/
                } else if (event.equals(Event.PIC_WEIXIN.toString())) {
                    /**弹出微信相册发图器的事件推送*/
                } else if (event.equals(Event.LOCATION_SELECT.toString())) {
                    /**弹出地理位置选择器的事件推送*/
                } else if (event.equals(Event.POI_CHECK_NOTIFY.toString())) {
                    /**门店审核事件推送*/
                } else if (event.equals(Event.CARD_PASS_CHECK.toString())) {
                    /**卡券通过审核*/
                } else if (event.equals(Event.CARD_NOT_PASS_CHECK.toString())) {
                    /**卡券未通过审核*/
                } else if (event.equals(Event.USER_GET_CARD.toString())) {
                    /**领取事件推送*/
                } else if (event.equals(Event.USER_DEL_CARD.toString())) {
                    /**删除事件推送*/
                } else if (event.equals(Event.USER_CONSUME_CARD.toString())) {
                    /**核销事件*/
                } else if (event.equals(Event.USER_VIEW_CARD.toString())) {
                    /**核销事件，进入会员卡事件推送*/
                } else if (event.equals(Event.USER_ENTER_SESSION_FROM_CARD.toString())) {
                    /**从卡券进入公众号会话事件推送*/
                }
            }
        }

        String xml = "";
        if (respMsg != null) {
            respMsg.setCreateTime(System.currentTimeMillis() / 1000);
            xml = xStream.toXML(respMsg);
        }
        return xml;
    }


    private String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    private String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
