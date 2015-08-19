package com.bogle.frame.sms.variable;

/**
 * 短信所有的方法注释
 * Created by Administrator on 2015/7/3.
 */
public enum SmsThing {
    REGISTER_EX,        //注册序列号
    REGIST_DETAIL_INFO,//注册企业
    LOGOUT,//注销
    GET_EACH_FEE,//查询单据
    GET_BALANCE,//查询余额
    CHARGE_UP,//充值
    SEND_SMS,//发送短信
    GET_MO,//获取上行短信
    GET_REPORT,//下行短信
    SERIAL_PWD_UPD,//修改密码
    SET_SMS_CONFIG;//修改配置
}
