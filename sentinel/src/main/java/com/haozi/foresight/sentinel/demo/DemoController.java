package com.haozi.foresight.sentinel.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author liuenhao
 * @date 2020/9/18 2:38 下午
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/demo")
    public Object demo(){
        return demoService.sayHello();
    }

    @GetMapping(value = "/restTemplateBySentinel")
    public Object testRestTemplate(){
        return restTemplate.getForObject("http://127.0.0.1:8080/demo",String.class);
    }
}
