package com.haozi.foresight.sentinel.feign;

import org.springframework.context.annotation.Bean;

/**
 * @author liuenhao
 * @date 2020/9/23 6:04 下午
 */
public class FeignConfiguration {
    @Bean
    public FeignServiceFallback feignServiceFallback(){
        return new FeignServiceFallback();
    }
}
