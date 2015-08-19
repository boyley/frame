package com.bogle.frame.sms.event;

import com.bogle.frame.sms.domain.Log;
import com.bogle.frame.sms.service.SmsApi;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Administrator on 2015/7/2.
 */
public class SmsInvokeEvent extends ApplicationEvent {

    private SmsApi smsApi;
    private Object[] args;//执行参数
    private Log log;//日志记录


    public SmsInvokeEvent(Object source, SmsApi smsApi, Object[] args, Log log) {
        super(source);
        this.smsApi = smsApi;
        this.args = args;
        this.log = log;
    }



    public Object[] getArgs() {
        return args;
    }

    public Log getLog() {
        return log;
    }

    public SmsApi getSmsApi() {
        return smsApi;
    }
}
