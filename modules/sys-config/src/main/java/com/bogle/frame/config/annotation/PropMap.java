package com.bogle.frame.config.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2015/7/3.
 */
@Target(value = {ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PropMap{

    String value();
}
