package com.haozi.cache.autoloadcache.controller;

import com.jarvis.cache.annotation.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuenhao
 * @date 2020/11/6 5:09 下午
 */
@Slf4j
@RestController
public class DemoController {


    @GetMapping("/queryNum")
    @Cache(expire = 60, expireExpression = "null == #retVal ? 30: 60", key = "'user-byid-' + #args[0]")
    public Map<String,Integer> queryNum(@RequestParam Integer i){
        log.info("input: {}",i);
        if (i == 0){
            return null;
        }
        return new HashMap<String, Integer>(){{
            put("num",i);
        }};
    }
}
