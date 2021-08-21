package com.example.aspect.advice;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 秋猫
 * @version 2021-08-20 15:59
 * @Description 描述
 */
public class ExceptionHandleHandler extends BaseMethodAdviceHandler<Object>{

    @Override
    public void onThrow(ProceedingJoinPoint point, Throwable e) {
        super.onThrow(point, e);
    }

    @Override
    public Object getOnThrow(ProceedingJoinPoint point, Throwable e) {
        Class<?> returnType = getTargetMethod(point).getReturnType();

        // 如果返回值类型是 Map 或者其子类
        if (Map.class.isAssignableFrom(returnType)) {
            Map<String, Object> result = new HashMap<>(4);
            result.put("success", false);
            result.put("message", "调用出错");

            return result;
        }

        return null;
    }
}
