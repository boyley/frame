package com.bogle.frame.sms.annotation;


import com.bogle.frame.sms.variable.SmsThing;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2015/7/3.
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Thing {
    SmsThing value();
}
