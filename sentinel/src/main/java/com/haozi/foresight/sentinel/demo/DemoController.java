package com.haozi.foresight.sentinel.demo;

import com.haozi.foresight.sentinel.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Resource
    private FeignService feignService;

    @GetMapping("/demo")
    public Object demo(@RequestParam(value = "v",defaultValue = "1") Integer v){
        return new Object[]{demoService.sayHello(v)};
    }

    @GetMapping("/hotParam")
    public Object hotParam(@RequestParam(value = "v",defaultValue = "1") Integer v){
        return new Object[]{demoService.hotParam(v)};
    }

    @GetMapping(value = "/restTemplateBySentinel")
    public Object testRestTemplate(@RequestParam(value = "v",defaultValue = "1") Integer v){
        return new String[]{restTemplate.getForObject("http://127.0.0.1:8080/demo?v=" + v, String.class)};
    }

    @GetMapping(value = "/feignBySentinel")
    public Object testFiegn(@RequestParam(value = "v",defaultValue = "1") Integer v){
        return feignService.demo(v);
    }
}
