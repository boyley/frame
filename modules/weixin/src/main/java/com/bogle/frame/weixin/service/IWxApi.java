package com.bogle.frame.weixin.service;

import com.bogle.frame.weixin.defines.OauthDefines;
import com.bogle.frame.weixin.defines.TicketType;
import com.bogle.frame.weixin.exception.WeixinException;
import com.bogle.frame.weixin.message.*;
import com.bogle.frame.weixin.message.template.TemplateMsg;
import com.bogle.frame.weixin.message.ticket.ReqTicket;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 */
public interface IWxApi extends IWexinApiUrl {

    String TOKEN_ID = "token_id=";

    /**
     * 网页授权获取用户基本信息
     * appid:公众号的唯一标识
     * redirect_uri:授权后重定向的回调链接地址，请使用urlencode对链接进行处理
     * code:返回类型，请填写code,改code由微信生成
     * scope:应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     * state:重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     */
    String SNSAPI = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

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
     * 微信页面config接入
     *
     * @param url
     * @return
     */
    Map<String, String> signature(final String url);

    /**
     * 获取基础交互的access_token信息
     *
     * @return
     */
    Token getToken() throws WeixinException;

    /**
     * 网页授权access_token
     *
     * @param code
     * @return
     */
    Token getToken(String code) throws WeixinException;


    /**
     * 获取jssdk的ticket
     * 1.jsapi_ticket是公众号用于调用微信JS接口的临时票据
     * 2.微信卡券接口中使用的签名凭证api_ticket
     *
     * @return
     */
    Ticket getTicket(TicketType ticketType) throws WeixinException;

    /**
     * ticket获取
     * 1. 投放卡券二维码ticket
     * 2. 生成带参数的二维码ticket
     *
     * @param reqTicket
     * @return
     */
    Ticket getTicket(ReqTicket reqTicket) throws WeixinException;

    /**
     * 获取二维码信息
     *
     * @param reqTicket
     * @return
     */
    Qrcode getQrcode(ReqTicket reqTicket) throws WeixinException;

    /**
     * 网页授权获取用户基本信息
     *
     * @param code
     * @return
     */
    OauthDefines oauth2(String code);

    /**
     * 根据url生成授权的url
     *
     * @param url
     * @param state
     * @return
     */
    String generateOauth2Url(String url, String state);

    /**
     * 获取用户信息
     *
     * @param url
     * @return
     */
    Fans getFans(String url) throws WeixinException;

    /**
     * 发送模板消息
     *
     * @param templateMsg
     * @return
     * @throws WeixinException
     */
    Template send(TemplateMsg templateMsg) throws WeixinException;


    /**
     * 自定义菜单
     * 1、自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
     * 2、一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。
     * 3、创建自定义菜单后，由于微信客户端缓存，需要24小时微信客户端才会展现出来。测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
     * 自定义菜单接口可实现多种类型按钮，如下：
     * 1、click：点击推事件
     * 用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event	的结构给开发者（参考消息接口指南），并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互；
     * 2、view：跳转URL
     * 用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的网页URL，可与网页授权获取用户基本信息接口结合，获得用户基本信息。
     * 3、scancode_push：扫码推事件
     * 用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描结果（如果是URL，将进入URL），且会将扫码的结果传给开发者，开发者可以下发消息。
     * 4、scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框
     * 用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，将扫码的结果传给开发者，同时收起扫一扫工具，然后弹出“消息接收中”提示框，随后可能会收到开发者下发的消息。
     * 5、pic_sysphoto：弹出系统拍照发图
     * 用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，会将拍摄的相片发送给开发者，并推送事件给开发者，同时收起系统相机，随后可能会收到开发者下发的消息。
     * 6、pic_photo_or_album：弹出拍照或者相册发图
     * 用户点击按钮后，微信客户端将弹出选择器供用户选择“拍照”或者“从手机相册选择”。用户选择后即走其他两种流程。
     * 7、pic_weixin：弹出微信相册发图器
     * 用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，将选择的相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，随后可能会收到开发者下发的消息。
     * 8、location_select：弹出地理位置选择器
     * 用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，随后可能会收到开发者下发的消息。
     * 9、media_id：下发消息（除文本消息）
     * 用户点击media_id类型按钮后，微信服务器会将开发者填写的永久素材id对应的素材下发给用户，永久素材类型可以是图片、音频、视频、图文消息。请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
     * 10、view_limited：跳转图文消息URL
     * 用户点击view_limited类型按钮后，微信客户端将打开开发者在按钮中填写的永久素材id对应的图文消息URL，永久素材类型只支持图文消息。请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
     *
     * @param buttons
     * @return
     */
    WxMsg customMenu(List<Button> buttons) throws WeixinException;

    /**
     * 接收处理消息
     *
     * @param reqMsg
     * @return
     */
    Serializable process(String reqMsg);
}
