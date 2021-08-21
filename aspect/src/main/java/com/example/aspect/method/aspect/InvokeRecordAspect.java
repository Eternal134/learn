package com.example.aspect.method.aspect;

import com.example.aspect.advice.InvokeRecordHandler;
import com.example.aspect.advice.MethodAdviceHandler;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 秋猫
 * @version 2021-08-20 14:45
 * @Description 描述
 */
@Aspect
@Component
@Order(1)
public class InvokeRecordAspect extends BaseMethodAspect {

    @Override
    @Pointcut("@annotation(com.example.aspect.anno.InvokeRecordAnno)")
    protected void pointcut() { }

    @Override
    protected Class<? extends MethodAdviceHandler<?>> getAdviceHandleType() {
        return InvokeRecordHandler.class;
    }
}
