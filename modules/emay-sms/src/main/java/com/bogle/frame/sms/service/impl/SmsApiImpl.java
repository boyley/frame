package com.bogle.frame.sms.service.impl;

import cn.emay.sdk.client.api.Client;
import cn.emay.sdk.client.api.MO;
import cn.emay.sdk.client.api.StatusReport;
import com.alibaba.fastjson.JSON;
import com.bogle.frame.config.domain.Config;
import com.bogle.frame.config.persistence.ConfigMapper;
import com.bogle.frame.config.util.Utils;
import com.bogle.frame.sms.annotation.Thing;
import com.bogle.frame.sms.config.SmsConfig;
import com.bogle.frame.sms.defines.CodeMsg;
import com.bogle.frame.sms.domain.Sign;
import com.bogle.frame.sms.persistence.SignMapper;
import com.bogle.frame.sms.service.SmsApi;
import com.bogle.frame.sms.variable.SmsThing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Administrator on 2015/6/30.
 */
@Service("smsApi")
public class SmsApiImpl implements SmsApi, ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private static final String TYPE = "SMS";

    private final static Logger log = LoggerFactory.getLogger(SmsApiImpl.class);

    private ApplicationContext applicationContext;

    private Client sdkclient;

    @Autowired
    private ConfigMapper configMapper;//configMapper

    @Autowired
    private SignMapper signMapper;//初始化注册信息

    private SmsConfig smsConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //防止重复执行。
        if (event.getApplicationContext().getParent() == null) {
            List<Config> configs = this.configMapper.selectByType(TYPE);//获取类型为短信的配置信息
            if (smsConfig == null) {
                smsConfig = new SmsConfig();
            }
            //获取数据库配置的常量值，初始化配置
            Utils.setMapValue(configs, smsConfig);
            setSmsConfig(smsConfig);
            if (SmsApiImpl.log.isInfoEnabled()) {
                log.info("短信配置信息:{}", JSON.toJSONString(smsConfig));
            }
        }
    }

    @Override
    @Thing(SmsThing.SET_SMS_CONFIG)
    public SmsConfig getSmsConfig() {
        return this.smsConfig;
    }

    @Override
    @Thing(SmsThing.REGISTER_EX)
    public Serializable registEx(String serialpass) {
        if (SmsApiImpl.log.isInfoEnabled()) {
            log.info("注册序列号:{}", JSON.toJSONString(serialpass));
        }
        return sdkclient.registEx(serialpass);
    }

    @Override
    @Thing(SmsThing.REGIST_DETAIL_INFO)
    public Serializable registDetailInfo(String name, String linkMan, String phoneNum, String mobile, String email, String fax, String address, String postcode) {
        return sdkclient.registDetailInfo(name, linkMan, phoneNum, mobile, email, fax, address, postcode);
    }

    @Override
    @Thing(SmsThing.LOGOUT)
    public Serializable logout() {
        if (SmsApiImpl.log.isInfoEnabled()) {
            SmsApiImpl.log.info("注销短信");
        }
        return sdkclient.logout();
    }

    @Override
    @Thing(SmsThing.GET_EACH_FEE)
    public double getEachFee() {
        double eachFee = sdkclient.getEachFee();
        if (SmsApiImpl.log.isInfoEnabled()) {
            SmsApiImpl.log.info("获取短信金额：{}", eachFee);
        }
        return eachFee;
    }

    @Override
    @Thing(SmsThing.GET_BALANCE)
    public double getBalance() throws Exception {
        double balance = sdkclient.getBalance();
        if (SmsApiImpl.log.isInfoEnabled()) {
            SmsApiImpl.log.info("获取短信余额：{}", balance);
        }
        return balance;
    }

    @Override
    @Thing(SmsThing.CHARGE_UP)
    public Serializable chargeUp(String cardNo, String cardPass) {
        Serializable result = sdkclient.chargeUp(cardNo, cardPass);
        if (log.isInfoEnabled()) {
            log.info("短信充值账号：{}，密码：", cardNo, cardPass);
            log.info("短信充值结果:{}", JSON.toJSONString(result));
        }
        return result;
    }

    @Override
    @Thing(SmsThing.SEND_SMS)
    public Serializable sendSMS(String[] mobiles, String smsContent, int smsPriority) {
        Serializable result = sdkclient.sendSMS(mobiles, smsContent, smsPriority);
        if (SmsApiImpl.log.isInfoEnabled()) {
            log.info("待发送的手机号:{},发送内容：{}", Arrays.asList(mobiles), smsContent);
            log.info("发送短信执行结果:{}", JSON.toJSONString(result));
        }
        return result;
    }

    @Override
    @Thing(SmsThing.SEND_SMS)
    public Serializable sendSMS(String[] mobiles, String smsContent, String addSerial, int smsPriority) {
        return sdkclient.sendSMS(mobiles, smsContent, addSerial, smsPriority);
    }

    @Override
    @Thing(SmsThing.SEND_SMS)
    public Serializable sendSMSEx(String[] mobiles, String smsContent, String srcCharset, int smsPriority) {
        return sdkclient.sendSMSEx(mobiles, smsContent, srcCharset, smsPriority);
    }

    @Override
    @Thing(SmsThing.SEND_SMS)
    public Serializable sendSMSEx(String[] mobiles, String smsContent, String addSerial, String srcCharset, int smsPriority) {
        return sdkclient.sendSMSEx(mobiles, smsContent, addSerial, srcCharset, smsPriority);
    }

    @Override
    @Thing(SmsThing.SEND_SMS)
    public Serializable sendSMSEx(String[] mobiles, String smsContent, String addSerial, String srcCharset, int smsPriority, long smsID) {
        return sdkclient.sendSMSEx(mobiles, smsContent, addSerial, srcCharset, smsPriority, smsID);
    }

    @Override
    @Thing(SmsThing.SEND_SMS)
    public Serializable sendScheduledSMS(String[] mobiles, String smsContent, String sendTime) {
        return sdkclient.sendScheduledSMS(mobiles, smsContent, sendTime);
    }

    @Override
    @Thing(SmsThing.SEND_SMS)
    public Serializable sendScheduledSMSEx(String[] mobiles, String smsContent, String sendTime, String srcCharset) {
        return sdkclient.sendScheduledSMSEx(mobiles, smsContent, sendTime, srcCharset);
    }

    @Override
    @Thing(SmsThing.SEND_SMS)
    public Serializable sendScheduledSMS(String[] mobiles, String smsContent, String sendTime, String addSerial) {
        return sdkclient.sendScheduledSMS(mobiles, smsContent, sendTime, addSerial);
    }

    @Override
    @Thing(SmsThing.SEND_SMS)
    public Serializable sendScheduledSMS(String[] mobiles, String smsContent, String sendTime, String addSerial, String srcCharset) {
        return sdkclient.sendScheduledSMS(mobiles, smsContent, sendTime, addSerial, srcCharset);
    }

    @Override
    @Thing(SmsThing.GET_MO)
    public List<MO> getMO() throws Exception {
        return sdkclient.getMO();
    }

    @Override
    @Thing(SmsThing.GET_REPORT)
    public List<StatusReport> getReport() throws Exception {
        return sdkclient.getReport();
    }

    @Override
    @Thing(SmsThing.SERIAL_PWD_UPD)
    public Serializable serialPwdUpd(String serialPwd, String serialPwdNew) {
        return sdkclient.serialPwdUpd(serialPwd, serialPwdNew);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    @Thing(SmsThing.SET_SMS_CONFIG)
    public void setSmsConfig(SmsConfig smsConfig) {
        Utils.validate(smsConfig);
        this.smsConfig = smsConfig;
        /**
         * 实例化客户端，如果客服端已经注册过就不需要再次注册
         */
        try {
            this.sdkclient = new Client(smsConfig.getSoftwareSerialNo(), smsConfig.getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据该配置校验是否已经注册过了
        Sign sign = signMapper.selectSelective(new Sign(smsConfig.getSoftwareSerialNo(), smsConfig.getPwd(), smsConfig.getKey()));
        if (sign == null || !sign.getLog().getThing().equals(SmsThing.REGISTER_EX.toString()) || (sign.getLog().getThing().equals(SmsThing.REGISTER_EX.toString()) && !sign.getLog().getCode().equals(String.valueOf(CodeMsg.SUCCESS.getErrorcode())))) {
            //未注册改序列号，进行序列号注册
            if (smsConfig.getAutoRegister() == true) {
                SmsApi smsApi = this.applicationContext.getBean(SmsApi.class);
                smsApi.registEx(smsConfig.getPwd());
            }
        }
    }
}
