package com.example.aspect.method.aspect;

import com.example.aspect.advice.MethodAdviceHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author 秋猫
 * @version 2021-08-20 11:49
 * @Description 描述
 */
public abstract class BaseMethodAspect implements ApplicationContextAware {

    /**
     * 切点，通过 @Pointcut 指定相关的注解
     */
    protected abstract void pointcut();

    /**
     * 对目标方法进行环绕增强处理，子类需通过pointcut()方法指定切点
     *
     * @param point 连接点
     * @return 方法执行返回值
     */
    @Around("pointcut()")
    public Object advice(ProceedingJoinPoint point) {
        // 获得切面绑定的方法增强处理器类型
        Class<? extends MethodAdviceHandler<?>> handlerType = getAdviceHandleType();
        // 从spring上下文中获得方法增强处理器的实现 Bean
        MethodAdviceHandler<?> adviceHandler = appContext.getBean(handlerType);
        // 使用方法增强处理器对目标方法进行增强处理
        return advice(point, adviceHandler);
    }

    private Object advice(ProceedingJoinPoint point, MethodAdviceHandler<?> adviceHandler) {
        // 执行之前，返回是否被允许执行
        boolean permitted = adviceHandler.onBefore(point);

        // 方法返回值
        Object result;
        // 是否抛出了异常
        boolean thrown = false;
        // 开始执行时间
        long startTime = System.currentTimeMillis();

        // 目标方法被允许执行
        if (permitted) {
            try {
                // 执行目标方法
                result = point.proceed();
            } catch (Throwable e) {
                // 抛出异常
                thrown = true;
                // 处理异常
                adviceHandler.onThrow(point, e);
                // 抛出异常时的返回值
                result = adviceHandler.getOnThrow(point, e);
            }
        } else {
            // 目标方法不允许执行时
            result = adviceHandler.getOnForbid(point);
        }

        // 结束
        adviceHandler.onComplete(point, startTime, permitted, thrown, result);

        return result;
    }

    /**
     * 获得切面绑定的方法增强处理器类型
     */
    protected abstract Class<? extends MethodAdviceHandler<?>> getAdviceHandleType();

    private ApplicationContext appContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }
}
