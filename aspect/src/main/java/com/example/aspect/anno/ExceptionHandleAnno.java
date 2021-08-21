package com.example.aspect.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author eleme
 * @version $Id: ExceptionHandleAnno.java, v 0.1 2021-08-20 3:36 下午 eleme Exp $$
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionHandleAnno {

    String value() default "";
}
