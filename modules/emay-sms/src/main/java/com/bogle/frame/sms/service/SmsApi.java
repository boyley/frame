package com.bogle.frame.sms.service;

import cn.emay.sdk.client.api.MO;
import cn.emay.sdk.client.api.StatusReport;
import com.bogle.frame.sms.annotation.Thing;
import com.bogle.frame.sms.config.SmsConfig;
import com.bogle.frame.sms.variable.SmsThing;


import java.io.Serializable;
import java.util.List;

/**
 * 3.1	实例化客户端
 * 3.2	注册序列号
 * int registEx(String serialpass);
 * 3.3	注册企业信息
 * int registDetailInfo(String name, String linkMan, String phoneNum, String mobile,String email, String fax, String address, String postcode)
 * 3.4	注销序列号
 * int logout();
 * 3.5	查询单价
 * 3.6	查询余额
 * 3.7	序列号充值
 * 3.8	发送即时短信
 * 3.9	发送即时短信（带扩展号）
 * 3.10	发送即时短信（带字符编码）
 * 3.11	发送即时短信（带扩展号和字符编码）
 * 3.12	发送即时短信（带扩展号，字符编码和短信ID）
 * 3.13	发送定时短信
 * 3.14	发送定时短信（带字符编码）
 * 3.15	发送定时短信（带扩展号）
 * 3.16	发送定时短信（带扩展号和字符编码）
 * 3.17	接收短信
 * 3.18	接收状态报告
 * 3.19	修改密码
 * <p>
 * Created by Administrator on 2015/6/30.
 */
public interface SmsApi {

    @Thing(SmsThing.SET_SMS_CONFIG)
    SmsConfig getSmsConfig();

    //实例化客户端

    /**
     * 注册序列号
     *
     * @param serialpass 软件序列号密码，密码（6位），必须输入
     * @return
     */
    @Thing(SmsThing.REGISTER_EX)
    Serializable registEx(String serialpass);

    /**
     * 注册企业信息
     *
     * @param name     企业名称(最多60字节)，必须输入
     * @param linkMan  联系人姓名(最多20字节)，必须输入
     * @param phoneNum 联系电话(最多20字节)，必须输入
     * @param mobile   联系手机(最多15字节)，必须输入
     * @param email    电子邮件(最多60字节)，必须输入
     * @param fax      联系传真(最多20字节)，必须输入
     * @param address  公司地址(最多60字节)，必须输入
     * @param postcode 邮政编码(最多6字节)，必须输入
     * @return
     */
    @Thing(SmsThing.REGIST_DETAIL_INFO)
    Serializable registDetailInfo(String name, String linkMan, String phoneNum, String mobile, String email, String fax, String address, String postcode);

    /**
     * 注销序列号
     * 用户注销以后就不能再识别发送短信，接受上行短信等接口，只有重新注册以后才能重新使用
     *
     * @return
     */
    @Thing(SmsThing.LOGOUT)
    Serializable logout();


    /**
     * 查询单价,返回一条短信的费用
     *
     * @return
     */
    @Thing(SmsThing.GET_EACH_FEE)
    double getEachFee();

    /**
     * 查询余额,获得序列号的剩余金额
     */
    @Thing(SmsThing.GET_BALANCE)
    double getBalance() throws Exception;

    /**
     * 序列号充值
     * 获得充值卡,可利用短信的方式进行充值
     *
     * @param cardNo   充值卡号
     * @param cardPass 充值卡密码
     * @return
     */
    @Thing(SmsThing.CHARGE_UP)
    Serializable chargeUp(String cardNo, String cardPass);

    /**
     * 发送即时短信,短信发送函数,调用该函数会即时的下发短信，支持单发和群发
     *
     * @param mobiles     手机号码(群发为字符串数组推荐最多为200个手机号码或以内)
     * @param smsContent  短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，请客户应用程序不要自己分割短信以免造成混乱)
     * @param smsPriority 优先级(级别从1到5的正整数，数字越大优先级越高，越先被发送)
     * @return
     */
    @Thing(SmsThing.SEND_SMS)
    Serializable sendSMS(String[] mobiles, String smsContent, int smsPriority);

    /**
     * 发送即时短信（带扩展号）
     * 扩展号:类似电话的分机号，它可以区分自己的某种业务代码或短信类别。
     * 带扩展号的需要申请才可使用，其它可参考发送即时短信（3.8节）
     *
     * @param mobiles     手机号码(群发为字符串数组推荐最多为200个手机号码或以内)
     * @param smsContent  短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，请客户应用程序不要自己分割短信以免造成混乱)
     * @param addSerial   扩展号 (长度小于15的字符串) 用户可通过扩展号自定义短信类别
     * @param smsPriority 优先级(级别从1到5的正整数，数字越大优先级越高，越先被发送)
     * @return
     */
    @Thing(SmsThing.SEND_SMS)
    Serializable sendSMS(String[] mobiles, String smsContent, String addSerial, int smsPriority);

    /**
     * 发送即时短信（带字符编码）
     * 参考 发送即时短信说明
     *
     * @param mobiles     手机号码(群发为字符串数组推荐最多为200个手机号码或以内)
     * @param smsContent  短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，请客户应用程序不要自己分割短信以免造成混乱)
     * @param srcCharset  短信内容的编码方式 只能填GBK
     * @param smsPriority 优先级(级别从1到5的正整数，数字越大优先级越高，越先被发送)
     * @return
     */
    @Thing(SmsThing.SEND_SMS)
    Serializable sendSMSEx(String[] mobiles, String smsContent, String srcCharset, int smsPriority);

    /**
     * 发送即时短信（带扩展号和字符编码）
     * 扩展号:类似电话的分机号，它可以区分自己的某种业务代码或短信类别。
     * 带扩展号的需要申请才可使用，其它可参考发送即时短信
     *
     * @param mobiles     手机号码(群发为字符串数组推荐最多为200个手机号码或以内)
     * @param smsContent  短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，请客户应用程序不要自己分割短信以免造成混乱)
     * @param addSerial   扩展号 (长度小于15的字符串) 用户可通过扩展号自定义短信类别
     * @param srcCharset  短信内容的编码方式 只能填GBK
     * @param smsPriority 优先级(级别从1到5的正整数，数字越大优先级越高，越先被发送)
     * @return
     */
    @Thing(SmsThing.SEND_SMS)
    Serializable sendSMSEx(String[] mobiles, String smsContent, String addSerial, String srcCharset, int smsPriority);

    /**
     * 发送即时短信（带扩展号，字符编码和短信ID）
     * 扩展号:类似电话的分机号，它可以区分自己的某种业务代码或短信类别。
     * 带扩展号的需要申请才可使用，其它可参考发送即时短信
     * 此方法可以调用（3.18节）的方法获取信息的状态报告。
     *
     * @param mobiles     手机号码(群发为字符串数组推荐最多为200个手机号码或以内)
     * @param smsContent  短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，请客户应用程序不要自己分割短信以免造成混乱)
     * @param addSerial   扩展号 (长度小于15的字符串) 用户可通过扩展号自定义短信类别
     * @param srcCharset  短信内容的编码方式 只能填GBK
     * @param smsPriority 优先级(级别从1到5的正整数，数字越大优先级越高，越先被发送)
     * @param smsID       短信ID，自定义唯一的消息ID，数字位数最大19位，与状态报告ID一一对应，需用户自定义ID规则确保ID的唯一性。如果smsID为0将获取不到相应的状态报告信息
     * @return
     */
    @Thing(SmsThing.SEND_SMS)
    Serializable sendSMSEx(String[] mobiles, String smsContent, String addSerial, String srcCharset, int smsPriority, long smsID);

    /**
     * 发送定时短信
     * 系统可根据自定义时间进行短信发送，该条短信会在指定的时间发送到用户的手机号中，支持单发和群发。
     *
     * @param mobiles    手机号码(群发为字符串数组推荐最多为200个手机号码或以内)
     * @param smsContent 短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，请客户应用程序不要自己分割短信以免造成混乱)
     * @param sendTime   定时时间.格式为：年年年年月月日日时时分分秒秒，例如20090801123030 表示2009年8月1日12点30分30秒该条短信会发送到用户手机
     * @return
     */
    @Thing(SmsThing.SEND_SMS)
    Serializable sendScheduledSMS(String[] mobiles, String smsContent, String sendTime);

    /**
     * 发送定时短信（带字符编码）
     * 参考发送定时短信
     *
     * @param mobiles    手机号码(群发为字符串数组推荐最多为200个手机号码或以内)
     * @param smsContent 短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，请客户应用程序不要自己分割短信以免造成混乱)
     * @param sendTime   定时时间.格式为：年年年年月月日日时时分分秒秒，例如20090801123030 表示2009年8月1日12点30分30秒该条短信会发送到用户手机
     * @param srcCharset 短信内容的编码方式（可为空）
     * @return
     */
    @Thing(SmsThing.SEND_SMS)
    Serializable sendScheduledSMSEx(String[] mobiles, String smsContent, String sendTime, String srcCharset);

    /**
     * 发送定时短信（带扩展号）
     * 扩展号:类似电话的分机号，它可以区分自己的某种业务代码或短信类别。
     * 带扩展号的需要申请才可使用，其它可参考发送定时短信
     *
     * @param mobiles    手机号码(群发为字符串数组推荐最多为200个手机号码或以内)
     * @param smsContent 短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，请客户应用程序不要自己分割短信以免造成混乱)
     * @param sendTime   定时时间.格式为：年年年年月月日日时时分分秒秒，例如20090801123030 表示2009年8月1日12点30分30秒该条短信会发送到用户手机
     * @param addSerial  扩展号 (长度小于15的字符串) 用户可通过扩展号自定义短信类别
     * @return
     */
    @Thing(SmsThing.SEND_SMS)
    Serializable sendScheduledSMS(String[] mobiles, String smsContent, String sendTime, String addSerial);

    /**
     * 发送定时短信（带扩展号和字符编码）
     * 扩展号:类似电话的分机号，它可以区分自己的某种业务代码或短信类别。
     * 带扩展号的需要申请才可使用，其它可参考发送定时短信
     *
     * @param mobiles    手机号码(群发为字符串数组推荐最多为200个手机号码或以内)
     * @param smsContent 短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，请客户应用程序不要自己分割短信以免造成混乱)
     * @param sendTime   定时时间.格式为：年年年年月月日日时时分分秒秒，例如20090801123030 表示2009年8月1日12点30分30秒该条短信会发送到用户手机
     * @param addSerial  扩展号 (长度小于15的字符串) 用户可通过扩展号自定义短信类别
     * @param srcCharset 短信内容的编码方式（可为空）
     * @return
     */
    @Thing(SmsThing.SEND_SMS)
    Serializable sendScheduledSMS(String[] mobiles, String smsContent, String sendTime, String addSerial, String srcCharset);

    /**
     * 接收短信
     * 从EUCP平台接收手机用户上行的短信，返回上行短信，该list的每个元素为一个MO，如果获取的结果为null则说明平台暂时还没有获取到上行信息供客户查询。
     * 接收短信的方法需要申请才可以使用。
     *
     * @return 接收短信集合，集合中的值是类型为MO的对象
     * @throws Exception
     */
    @Thing(SmsThing.GET_MO)
    List<MO> getMO() throws Exception;

    /**
     * 接收状态报告
     * 接收短信发送状态报告
     *
     * @return
     * @throws Exception
     */
    @Thing(SmsThing.GET_REPORT)
    List<StatusReport> getReport() throws Exception;

    /**
     * 修改密码
     *
     * @param serialPwd    旧密码
     * @param serialPwdNew 新密码，6位，必须是数字字符串，必须输入
     * @return
     */
    @Thing(SmsThing.SERIAL_PWD_UPD)
    Serializable serialPwdUpd(String serialPwd, String serialPwdNew);

    /**
     * 设置配置信息重新激活
     * @param smsConfig
     */
    @Thing(SmsThing.SET_SMS_CONFIG)
    void setSmsConfig(SmsConfig smsConfig);
}
