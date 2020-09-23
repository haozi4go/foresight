package com.haozi.foresight.sentinel.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liuenhao
 * @date 2020/9/18 4:31 下午
 */
@FeignClient(name = "feign-service-provider",url = "http://127.0.0.1:8080",
        fallback = FeignServiceFallback.class, configuration = FeignConfiguration.class)
public interface FeignService {
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    String[] demo(@RequestParam Integer v);
}
