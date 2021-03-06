package com.example.aspect.advice;

import com.alibaba.fastjson.JSON;
import com.example.aspect.anno.InvokeRecordAnno;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author 秋猫
 * @version 2021-08-20 13:54
 * @Description 描述
 */
@Component
public class InvokeRecordHandler extends BaseMethodAdviceHandler<Object> {

    @Override
    public void onComplete(ProceedingJoinPoint point, long startTime, boolean permitted, boolean thrown, Object result) {
        String methodDesc = getMethodDesc(point);
        Object[] args = point.getArgs();
        long costTime = System.currentTimeMillis() - startTime;

        logger.warn("\n{} 执行结束, 耗时={}ms，入参={}, 出参={}",
                methodDesc, costTime,
                JSON.toJSONString(args, true),
                JSON.toJSONString(result, true));
    }

    @Override
    protected String getMethodDesc(ProceedingJoinPoint point) {
        Method targetMethod = getTargetMethod(point);
        // 获得方法上的 InvokeRecordAnno
        InvokeRecordAnno anno = targetMethod.getAnnotation(InvokeRecordAnno.class);
        String description = anno.value();

        // 如果没有指定方法说明，那么使用默认的方法说明
        if (StringUtils.hasText(description)) {
            description = super.getMethodDesc(point);
        }

        return description;
    }
}
