package com.haozi.foresight.jetcache.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liuenhao
 * @date 2020/10/23 2:08 下午
 */
@Getter
@Setter
public class JsonCacheObject<V> {

    private String className;
    private V realObj;

    public JsonCacheObject() {
    }

    public JsonCacheObject(String className, V realObj) {
        this.className = className;
        this.realObj = realObj;
    }

    // ignore get and set methods
}
