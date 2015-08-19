package com.bogle.frame.weixin.controller;

import com.bogle.frame.weixin.defines.OauthDefines;
import com.bogle.frame.weixin.defines.WxCode;
import com.bogle.frame.weixin.service.impl.WxApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/18.
 */
@RestController
@RequestMapping("/weixin")
public class WinxinController {

    private static final Logger log = LoggerFactory.getLogger(WinxinController.class);

    @Autowired
    private WxApi wxApi;

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public
    @ResponseBody
    Serializable process(
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam("echostr") String echostr) {
        if (wxApi.checkSignature(signature, timestamp, nonce,
                echostr)) {
            return echostr;
        } else {
            return "";
        }
    }

    @RequestMapping(value = "/process", method = RequestMethod.POST, headers = "Accept=application/xml")
    public
    @ResponseBody
    Serializable process(@RequestBody String requestbody, HttpServletRequest request) {
        String host = request.getScheme() + "://" + request.getServerName(); //服务器地址request.getServerName()
        if (log.isInfoEnabled()) {
            log.info("获取fans发送到微信后台的信息", requestbody);
        }
        Serializable result = this.wxApi.process(requestbody);
        if (log.isInfoEnabled()) {
            log.info("后台返回给微信fans的用户信息", result);
        }
        return result;
    }

    @RequestMapping(value = "/oauthsns")
    public ModelAndView oauthsns(@RequestParam(value = "code", required = false) String code, @RequestParam(value = "view") String viewName, @RequestParam(value = "state") String state, HttpServletRequest request) {
        String url = request.getScheme() + "://" + request.getServerName() + request.getServletPath() + "?" + request.getQueryString();

        // js_sdk签名
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        //页面授权获取网页信息
        OauthDefines oauthDefines = this.wxApi.oauth2(code);
        //授权成功
        //授权成功
        if (oauthDefines.getCode() == WxCode.SUCCESS) {
            Map<String,String> signature = this.wxApi.signature(url);
            modelAndView.addObject("signature",signature);
            log.info("授权成功");
        } else {
            //授权失败，重新封装url，跳转到微信授权，通过微信重新跳转到本方法进行授权
            int lastIndex = url.lastIndexOf("&code=");
            if (lastIndex > 0) {
                url = url.substring(0, lastIndex);
            }
            String redirect = this.wxApi.generateOauth2Url(url, state);
            modelAndView.setViewName("redirect:" + redirect);
        }

        return modelAndView;
    }
}
