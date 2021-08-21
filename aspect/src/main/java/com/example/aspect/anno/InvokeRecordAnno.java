package com.example.aspect.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author eleme
 * @version $Id: InvokeRecordAnno.java, v 0.1 2021-08-20 1:52 下午 eleme Exp $$
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InvokeRecordAnno {

    /**
     * 调用说明
     */
    String value() default "";
}
