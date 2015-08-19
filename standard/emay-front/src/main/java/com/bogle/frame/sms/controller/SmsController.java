package com.bogle.frame.sms.controller;

import com.alibaba.fastjson.JSON;
import com.bogle.frame.sms.defines.CodeMsg;
import com.bogle.frame.sms.message.Message;
import com.bogle.frame.sms.service.SmsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Administrator on 2015/7/10.
 */
@RestController
@RequestMapping("/sms")
public class SmsController {
    private static final Logger log = LoggerFactory.getLogger(SmsController.class);
    @Autowired
    private SmsApi smsApi;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/send", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, method = RequestMethod.POST, params = "version")
    public
    @ResponseBody
    Message send(@RequestBody final Message message) {
        if (log.isInfoEnabled()) {
            log.info("接收待发送的短信信息:{}", JSON.toJSONString(message));
        }
        CodeMsg result = (CodeMsg) smsApi.sendSMS(message.getMobiles(), message.getContent(), message.getPriority());
        message.setErrorcode(result.getErrorcode());
        message.setErrmsg(result.getErrmsg());
        message.setSource(result.getSource());
        if (log.isInfoEnabled()) {
            log.info("返回发送短信结果信息:{}", JSON.toJSONString(message));
        }
        return message;
    }
}
