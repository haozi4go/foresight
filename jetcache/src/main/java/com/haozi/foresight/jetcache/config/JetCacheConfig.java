package com.haozi.foresight.jetcache.config;

import com.alicp.jetcache.anno.support.SpringConfigProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * @author liuenhao
 * @date 2020/10/23 2:09 下午
 */
@Configuration
public class JetCacheConfig {
    @Bean(name = "jsonPolicy")
    public JsonSerialPolicy jsonSerializerPolicy() {
        return new JsonSerialPolicy();
    }

    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider() {
            @Override
            public Function<byte[], Object> parseValueDecoder(String valueDecoder) {
                if (valueDecoder.equalsIgnoreCase("myJson")) {
                    return new JsonSerialPolicy().decoder();
                }
                return super.parseValueDecoder(valueDecoder);
            }

            @Override
            public Function<Object, byte[]> parseValueEncoder(String valueEncoder) {
                if (valueEncoder.equalsIgnoreCase("myJson")) {
                    return new JsonSerialPolicy().encoder();
                }
                return super.parseValueEncoder(valueEncoder);
            }
        };
    }
}
