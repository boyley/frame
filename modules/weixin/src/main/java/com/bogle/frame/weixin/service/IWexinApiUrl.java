package com.bogle.frame.weixin.service;

/**
 * 后台
 * Created by Administrator on 2015/8/17.
 */
public interface IWexinApiUrl {

    /**
     * 基础access_token
     * grant_type	是	获取access_token填写client_credential
     * appid	是	第三方用户唯一凭证
     * secret	是	第三方用户唯一凭证密钥，即appsecret
     */
    String BASE_ACCESS_TOKEN_API_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    /**
     * 网页授权access_token
     * 参数	是否必须	说明
     * appid	是	公众号的唯一标识
     * secret	是	公众号的appsecret
     * code	是	填写第一步获取的code参数
     * grant_type	是	填写为authorization_code
     */
    String OAUTH2_ACCESS_TOKEN_API_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    /**
     * 调用微信JS接口的临时票据
     * 参数	是否必须	说明
     * access_token	是	基础的access_token
     */
    String JSAPI_TICKET_API_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

    /**
     * 调微信卡券接口中使用的签名凭证api_ticket
     * 参数	是否必须	说明
     * access_token	是	基础的access_token
     */
    String JSAPI_CARD_TICKET_URL_API_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=wx_card";

    /**
     * 投放卡券二维码ticket
     * 参数	是否必须	说明
     * access_token	是	基础的access_token
     */
    String CARD_TICKET_URL_API_URL = "https://api.weixin.qq.com/card/qrcode/create?access_token=%s";

    /**
     * 创建二维码ticket
     * 参数	是否必须	说明
     * access_token	是	基础的access_token
     */
    String QRCODE_TICKET_URL_API_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";

    /**
     * 根据二维码获取二维码图片
     * 参数	是否必须	说明
     * ticket	是	获取二维码的ticket
     */
    String QRCODE_URL_API_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";

    //拉取用户信息(需scope为 snsapi_userinfo) http：GET（请使用https协议）
    String SNSAPI_USERINFO_API_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    /**
     * 发送模板消息
     * 参数	是否必须	说明
     * access_token	是	基础的access_token
     */
    String SEND_TEMPLATE_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";
}
