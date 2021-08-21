package com.example.aspect.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author 秋猫
 * @version 2021-08-20 11:12
 * @Description 方法增强处理器
 *
 * @param <R> 目标方法返回值的类型
 */
public interface MethodAdviceHandler<R> {

    /**
     * 目标方法执行之前的判断，判断目标方法是否允许执行。默认返回true，即 默认允许执行
     *
     * @param point 目标方法的连接点
     * @return 返回true则允许调用目标方法；返回false则表示禁止调用目标方法。
     * 当返回false时，此时会先调用getOnForbid方法获得被禁止执行时的返回值，
     * 然后调用onComplete方法结束切面
     */
    default boolean onBefore(ProceedingJoinPoint point) { return true; }

    /**
     * 禁止调用目标方法时（即onBefore返回false），执行该方法获得返回值，默认返回null
     *
     * @param point 目标方法的连接点
     * @return 禁止调用方法时的返回值
     */
    default R getOnForbid(ProceedingJoinPoint point) { return null; }

    /**
     * 目标方法抛出异常时，执行的动作
     *
     * @param point 目标方法的连接点
     * @param e 异常
     */
    void onThrow(ProceedingJoinPoint point, Throwable e);

    /**
     * 抛出异常时的返回值，默认为null
     *
     * @param point 目标方法的连接点
     * @param e 异常
     * @return 抛出异常时的返回值
     */
    default R getOnThrow(ProceedingJoinPoint point, Throwable e) { return null; }

    /**
     * 目标方法完成时，执行的动作
     *
     * @param point 目标方法的连接点
     * @param startTime 执行的开始时间
     * @param permitted 目标方法是否允许被执行
     * @param thrown 目标方法执行时是否抛出异常
     * @param result 执行获得的结果
     */
    default void onComplete(ProceedingJoinPoint point, long startTime, boolean permitted, boolean thrown, Object result) {}
}
