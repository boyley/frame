package com.bogle.frame.weixin.event;

import com.bogle.frame.weixin.message.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Administrator on 2015/8/18.
 */
public class MessageEvent extends ApplicationEvent {

    private com.bogle.frame.weixin.domain.Message message;

    public MessageEvent(Object source, Message message,String appId) {
        super(source);
        this.message = new com.bogle.frame.weixin.domain.Message();
        BeanUtils.copyProperties(message,this.message);
        this.message.setAppId(appId);
    }

    public com.bogle.frame.weixin.domain.Message getMessage() {
        return message;
    }
}
