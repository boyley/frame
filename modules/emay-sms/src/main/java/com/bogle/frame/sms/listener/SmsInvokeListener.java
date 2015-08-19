package com.bogle.frame.sms.listener;

import com.alibaba.fastjson.JSON;
import com.bogle.frame.sms.config.SmsConfig;
import com.bogle.frame.sms.domain.*;
import com.bogle.frame.sms.event.SmsInvokeEvent;
import com.bogle.frame.sms.persistence.ChargeLogMapper;
import com.bogle.frame.sms.persistence.RegisterInfoMapper;
import com.bogle.frame.sms.persistence.SignMapper;
import com.bogle.frame.sms.persistence.TaskMapper;
import com.bogle.frame.sms.service.SmsApi;
import com.bogle.frame.sms.variable.SendVariable;
import com.bogle.frame.sms.variable.SmsThing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 微信方法监听器
 * Created by Administrator on 2015/7/2.
 */
@Component
public class SmsInvokeListener implements ApplicationListener<SmsInvokeEvent> {

    private final static Logger log = LoggerFactory.getLogger(SmsInvokeListener.class);

    @Autowired
    private SignMapper signMapper;

    @Autowired
    private RegisterInfoMapper registerInfoMapper;

    @Autowired
    private ChargeLogMapper chargeLogMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public void onApplicationEvent(SmsInvokeEvent event) {
        if (log.isInfoEnabled()) {
            SmsInvokeListener.log.info("短信sdk操作:{}", JSON.toJSONString(event));
        }
        SmsApi smsApi = event.getSmsApi();
        SmsConfig config = smsApi.getSmsConfig();
        Log log = event.getLog();
        Object[] args = event.getArgs();
        String thing = log.getThing();
        String invoke = log.getInvoke();
        if (thing.equals(SmsThing.REGISTER_EX.toString()) || thing.equals(SmsThing.LOGOUT.toString())) {
            //注册序列号, //注销
            Sign smsSign = new Sign(config.getSoftwareSerialNo(), config.getPwd(), config.getSpecialNo(), config.getKey(), log.getId());
            signMapper.insertSelective(smsSign);
            if (SmsInvokeListener.log.isInfoEnabled()) {
                SmsInvokeListener.log.info("注册/注销序列号:{}", JSON.toJSONString(smsSign));
            }
        } else if (thing.equals(SmsThing.REGIST_DETAIL_INFO.toString())) {
            //注册企业信息
            String name = args[0] != null ? args[0].toString() : null;
            String linkMan = args[1] != null ? args[1].toString() : null;
            String phoneNum = args[2] != null ? args[2].toString() : null;
            String mobile = args[3] != null ? args[3].toString() : null;
            String email = args[4] != null ? args[4].toString() : null;
            String fax = args[5] != null ? args[5].toString() : null;
            String address = args[6] != null ? args[6].toString() : null;
            String postcode = args[7] != null ? args[7].toString() : null;
            RegisterInfo registerInfo = new RegisterInfo(config.getSoftwareSerialNo(), name, linkMan, phoneNum, mobile, email, fax, address, postcode, log.getId());
            registerInfoMapper.insertSelective(registerInfo);
            if (SmsInvokeListener.log.isInfoEnabled()) {
                SmsInvokeListener.log.info("注册企业信息:{}", JSON.toJSONString(registerInfo));
            }
        } else if (thing.equals(SmsThing.CHARGE_UP.toString())) {
            //充值记录
            String cardNo = args[0] != null ? args[0].toString() : null;
            String cardPass = args[1] != null ? args[1].toString() : null;
            ChargeLog chargeLog = new ChargeLog(cardNo, cardPass, log.getId());
            this.chargeLogMapper.insertSelective(chargeLog);
            if (SmsInvokeListener.log.isInfoEnabled()) {
                SmsInvokeListener.log.info("充值:{}", JSON.toJSONString(chargeLog));
            }
        } else if (invoke.matches(".*send.*")) {
            //发送信息
            String[] mobiles = args[0] != null ? (String[]) args[0] : null;
            String smsContent = args[1] != null ? args[1].toString() : null;
            Task task = new Task(System.currentTimeMillis(), SendVariable.Z, Arrays.asList(mobiles).toString(), smsContent, log.getId());
            taskMapper.insertSelective(task);
            if (SmsInvokeListener.log.isInfoEnabled()) {
                SmsInvokeListener.log.info("发送短信:{}", JSON.toJSONString(task));
            }
        }
    }
}
