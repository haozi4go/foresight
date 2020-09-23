package com.haozi.foresight.sentinel.utils;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author liuenhao
 * @date 2020/9/18 6:21 下午
 */
public class BlockHandlerUtil {
    public static ClientHttpResponse handle(HttpRequest request, byte[] body,
                                            ClientHttpRequestExecution execution, BlockException exception) {
        System.err.println("BlockHandlerUtil handle: " + exception.getRule().getLimitApp() + " " +exception.getRule().getResource());
        return new SentinelClientHttpResponse("block");
    }
}
