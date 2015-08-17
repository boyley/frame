package com.bogle.frame.weixin.component;

import com.alibaba.fastjson.JSON;
import com.bogle.frame.common.HttpClientUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 */
@Component
public class Http {

    /**
     * http post 发送请求
     *
     * @param url
     * @param params
     * @return
     */
    public <T> T httpPost(String url, Map<String, String> params, Class<T> type) {
        byte[] bytes = HttpClientUtil.httpPost(url, params);
        return JSON.parseObject(bytes, type);
    }

    public <T> T httpPost(String url, String params, Class<T> type) {
        byte[] bytes = HttpClientUtil.httpPost(url, params);
        return JSON.parseObject(bytes, type);
    }

    public <T> T httpGet(String url, Class<T> type) {
        byte[] bytes = HttpClientUtil.httpGet(url);
        return JSON.parseObject(bytes, type);
    }
}
