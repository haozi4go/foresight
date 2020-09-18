package com.haozi.foresight.sentinel.resttemplate;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuenhao
 * @date 2020/9/18 6:06 下午
 */
@Component
public class RestTemplateSentinelConfig {
    @Bean
    @SentinelRestTemplate(blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    public void handleException(){
        System.out.println("handleException:  ttt");
    }
}
