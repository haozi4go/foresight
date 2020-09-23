package com.haozi.foresight.sentinel.feign;

/**
 * @author liuenhao
 * @date 2020/9/23 6:04 下午
 */
public class FeignServiceFallback implements FeignService {
    @Override
    public String[] demo(Integer v) {
        return new String[]{"feign fallback, v=" + v};
    }
}
