package com.haozi.foresight.jetcache.demo;

import com.alicp.jetcache.anno.Cached;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuenhao
 * @date 2020/10/22 10:19 上午
 */
@RestController
public class Controller {
    @Cached(name="demo.jetcache", expire = 3600)
    @GetMapping("/demo")
    public Object demo(@RequestParam Long id, @RequestParam List<String> name){
        Map<String,Object> retVal = new HashMap<>();
        System.out.println("test cache");
        retVal.put("id",id);
        retVal.put("names",name);
        return retVal;
    }
}
