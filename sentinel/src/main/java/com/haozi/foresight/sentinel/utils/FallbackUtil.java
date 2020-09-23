package com.haozi.foresight.sentinel.utils;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author liuenhao
 * @date 2020/9/21 4:39 下午
 */
public class FallbackUtil {
    public static ClientHttpResponse fallback(HttpRequest request, byte[] body,
                                              ClientHttpRequestExecution execution, BlockException exception){
        System.err.println("FallbackUtil: " + exception.getClass().getCanonicalName());
        return new SentinelClientHttpResponse("fallback");
    }
}
