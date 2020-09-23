package com.haozi.foresight.sentinel.demo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author liuenhao
 * @date 2020/9/18 2:55 下午
 */
@Service
public class DemoService {

    @SentinelResource(value = "sayHello",
            fallback = "sayHelloFallback",exceptionsToIgnore = {IllegalArgumentException.class})
    public Object sayHello(Integer v){
        if (v < 0){
            throw new IllegalArgumentException("can not less 0");
        }
        if( v%2 == 0){
            throw new RuntimeException("round error");
        }
        return "sentinal: v=" + v;
    }

    public Object sayHelloFallback(Integer v){
        return "sentinal fallback: v=" + v;
    }

    @SentinelResource(value = "hotParam")
    public Object hotParam(Integer v){
        return v;
    }

}
