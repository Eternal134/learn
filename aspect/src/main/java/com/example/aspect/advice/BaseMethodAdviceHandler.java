package com.example.aspect.advice;

import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import com.alibaba.fastjson.JSON;

/**
 * @author 秋猫
 * @version 2021-08-20 11:35
 * @Description 描述
 */
public abstract class BaseMethodAdviceHandler<R> implements MethodAdviceHandler<R> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onThrow(ProceedingJoinPoint point, Throwable e) {
        String methodDesc = getMethodDesc(point);
        Object[] args = point.getArgs();
        logger.error("{} 执行时出错，入参={}", methodDesc, JSON.toJSONString(args, true), e);
    }

    /**
     * 获得方法描述，目标类名、方法名
     *
     * @param point 连接点
     * @return 目标类名、执行方法名
     */
    protected String getMethodDesc(ProceedingJoinPoint point) {
        // 获得被代理的类
        Object target = point.getTarget();
        String className = target.getClass().getSimpleName();

        Signature signature = point.getSignature();
        String method = signature.getName();

        return className + "." + method;
    }

    /**
     * @param point 连接点
     * @return 被代理的方法
     */
    protected Method getTargetMethod(ProceedingJoinPoint point) {
        // 获得方法签名
        Signature signature = point.getSignature();
        // Spring AOP 只有方法连接点，所以Signature一定是MethodSignature
        return ((MethodSignature) signature).getMethod();
    }
}
