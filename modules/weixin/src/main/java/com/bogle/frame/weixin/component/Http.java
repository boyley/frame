package com.bogle.frame.weixin.component;

import com.alibaba.fastjson.JSON;
import com.bogle.frame.common.HttpClientUtil;
import com.bogle.frame.weixin.exception.WeixinException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 */
@Component
public class Http {

    private final static Logger log = LoggerFactory.getLogger(Http.class);

    /**
     * http post 发送请求
     *
     * @param url
     * @param params
     * @return
     */
    public <T> T httpPost(String url, Map<String, String> params, Class<T> type) throws WeixinException {
        byte[] bytes = HttpClientUtil.httpPost(url, params);
        return JSON.parseObject(bytes, type);
    }

    public <T> T httpPost(String url, Object params, Class<T> type) {
        String jsonString = JSON.toJSONString(params);
        log.info("url:" + url + "，params:" + jsonString + "，type:" + type);
        byte[] bytes = HttpClientUtil.httpPost(url, jsonString);
        return JSON.parseObject(bytes, type);
    }

    public <T> T httpGet(String url, Class<T> type) throws WeixinException {
        log.info("url:" + url + "，type:" + type);
        byte[] bytes = HttpClientUtil.httpGet(url);
        return JSON.parseObject(bytes, type);
    }

    public byte[] httpGet(String url) throws WeixinException {
        return HttpClientUtil.httpGet(url);
    }
}
