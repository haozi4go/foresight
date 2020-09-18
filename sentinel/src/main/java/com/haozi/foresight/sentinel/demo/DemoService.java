package com.haozi.foresight.sentinel.demo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author liuenhao
 * @date 2020/9/18 2:55 下午
 */
@Service
public class DemoService {

    @SentinelResource(value = "sayHello")
    public Object sayHello(){
        return "sentinal";
    }
}
