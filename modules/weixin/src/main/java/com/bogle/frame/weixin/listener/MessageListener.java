package com.bogle.frame.weixin.listener;

import com.bogle.frame.weixin.domain.Message;
import com.bogle.frame.weixin.event.MessageEvent;
import com.bogle.frame.weixin.persistence.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2015/8/18.
 */
@Component
public class MessageListener implements ApplicationListener<MessageEvent> {

    @Autowired
    private MessageMapper messageMapper;

    @Async
    @Override
    public void onApplicationEvent(MessageEvent event) {
        Message message = event.getMessage();
        messageMapper.insertSelective(message);
    }
}
