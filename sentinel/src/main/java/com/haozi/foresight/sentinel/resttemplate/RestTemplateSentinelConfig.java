package com.haozi.foresight.sentinel.resttemplate;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.haozi.foresight.sentinel.utils.BlockHandlerUtil;
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
    @SentinelRestTemplate(blockHandler = "handle", blockHandlerClass = BlockHandlerUtil.class)
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
