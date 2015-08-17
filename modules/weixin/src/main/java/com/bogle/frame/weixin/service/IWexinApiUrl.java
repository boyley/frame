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
    String OAUTH2_ACCESS_TOKEN_API_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=%s";
}
