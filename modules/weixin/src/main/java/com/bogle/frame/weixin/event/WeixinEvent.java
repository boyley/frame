package com.bogle.frame.weixin.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Administrator on 2015/8/18.
 */
public class WeixinEvent extends ApplicationEvent {

    private Object returnValue;
    private Object[] args;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public WeixinEvent(Object source, Object returnValue, Object[] args) {
        super(source);
        this.returnValue = returnValue;
        this.args = args;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public Object[] getArgs() {
        return args;
    }
}
