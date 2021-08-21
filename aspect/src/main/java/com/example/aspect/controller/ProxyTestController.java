package com.example.aspect.controller;


import com.example.aspect.anno.InvokeRecordAnno;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 秋猫
 * @version 2021-08-20 14:54
 * @Description 描述
 */
@RestController
@RequestMapping("test")
public class ProxyTestController {

    @InvokeRecordAnno("测试代理模式")
    public Map<String, Object> testProxy() {
        Map<String, Object> result = new HashMap<>(4);
        result.put("1", "小红");
        result.put("2", "小明");
        return result;
    }
}
