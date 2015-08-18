package com.bogle.frame.weixin.aspect;

import com.bogle.frame.weixin.defines.WxCode;
import com.bogle.frame.weixin.event.WeixinEvent;
import com.bogle.frame.weixin.exception.WeixinException;
import com.bogle.frame.weixin.message.WxMsg;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2015/8/18.
 */
@Aspect
@Component
public class WeixinAspect {

    private final static Logger log = LoggerFactory.getLogger(WeixinAspect.class);

    @Autowired
    private ApplicationEventPublisher publisher;

    @Pointcut("execution (* com.bogle.frame.weixin.component.Http.* (..)) ")
    public void aspect() {
    }

    /*
    * 配置前置通知,使用在方法aspect()上注册的切入点
    * 同时接受JoinPoint切入点对象,可以没有该参数
    */
    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("before " + joinPoint);
        }
    }

    //配置后置通知,使用在方法aspect()上注册的切入点
    @After("aspect()")
    public void after(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("after " + joinPoint);
        }
    }

    @Around(value = "aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue = null;

        //访问目标方法的参数：
        Object[] args = joinPoint.getArgs();
        //执行目标方法
        returnValue = joinPoint.proceed(args);
        if (returnValue instanceof WxMsg) {
            publisher.publishEvent(new WeixinEvent(this,returnValue,args));
            WxMsg message = (WxMsg) returnValue;
            WxCode wxCode = handleException(message);
            if (wxCode != wxCode.SUCCESS) {
                throw new WeixinException("微信错误消息：" + wxCode.getErrmsg(), wxCode);
            }
        }
        return returnValue;
    }

    //配置后置返回通知,使用在方法aspect()上注册的切入点
    @AfterReturning("aspect()")
    public void afterReturn(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("afterReturn " + joinPoint);
        }
    }

    //配置抛出异常后通知,使用在方法aspect()上注册的切入点
    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Throwable ex) throws Throwable {
        if (log.isInfoEnabled()) {
            log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
        }
        throw ex;
    }

    private WxCode handleException(WxMsg wxMsg) throws WeixinException {
        WxCode[] values = WxCode.values();
        for (WxCode status : values) {
            int errcode = wxMsg.getErrcode();
            if (errcode != 0 && status.getErrorcode() == errcode) {
                if (log.isErrorEnabled()) {
                    log.error("微信错误消息：" + status.getErrmsg());
                }
                return status;
            }
        }
        return WxCode.SUCCESS;
    }
}
