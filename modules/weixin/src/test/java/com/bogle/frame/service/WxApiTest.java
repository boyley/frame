package com.bogle.frame.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bogle.frame.Application;
import com.bogle.frame.weixin.message.Token;
import com.bogle.frame.weixin.service.impl.WxApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2015/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class WxApiTest {

    private final static Logger log = LoggerFactory.getLogger(WxApiTest.class);

    @Autowired
    private WxApi wxApi;

    @Test
    public void getToken() {
        Token token = this.wxApi.getToken();
        println(token);
    }


    private void println(Object target) {
        log.info(JSON.toJSONString(target, SerializerFeature.PrettyFormat));
    }
}
