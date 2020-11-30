//package com.haozi.cache.autoloadcache.config;
//
//import com.jarvis.cache.CacheHandler;
//import com.jarvis.cache.ICacheManager;
//import com.jarvis.cache.clone.ICloner;
//import com.jarvis.cache.redis.ShardedJedisCacheManager;
//import com.jarvis.cache.script.AbstractScriptParser;
//import com.jarvis.cache.script.SpringELParser;
//import com.jarvis.cache.serializer.ISerializer;
//import com.jarvis.cache.serializer.JdkSerializer;
//import com.jarvis.cache.to.AutoLoadConfig;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisShardInfo;
//import redis.clients.jedis.ShardedJedisPool;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author liuenhao
// * @date 2020/11/6 5:01 下午
// */
//@Configuration
//public class AutoLoadSelfConfig {
//    @Bean
//    public AutoLoadConfig autoLoadConfig(){
//        AutoLoadConfig autoLoadConfig = new AutoLoadConfig();
//        autoLoadConfig.setNamespace("test");
//        autoLoadConfig.setThreadCnt(10);
//        autoLoadConfig.setMaxElement(200);
//        autoLoadConfig.setPrintSlowLog(true);
//        autoLoadConfig.setSlowLoadTime(500);
//        autoLoadConfig.setSortType(1);
//        autoLoadConfig.setCheckFromCacheBeforeLoad(true);
//        autoLoadConfig.setAutoLoadPeriod(50);
//        return autoLoadConfig;
//    }
//
//    @Bean
//    public JdkSerializer jdkSerializer(){
//        return new JdkSerializer();
//    }
//
//    @Bean
//    public SpringELParser springELParser(){
//        return new SpringELParser();
//    }
//
//    @Bean
//    public ShardedJedisCacheManager cacheManager(@Qualifier("jdkSerializer") ISerializer iSerializer){
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(2000);
//        jedisPoolConfig.setMaxIdle(100);
//        jedisPoolConfig.setMinIdle(50);
//        jedisPoolConfig.setMaxWaitMillis(2000);
//        jedisPoolConfig.setTestOnBorrow(false);
//        jedisPoolConfig.setTestOnReturn(false);
//        jedisPoolConfig.setTestWhileIdle(false);
//
//        JedisShardInfo jedisShardInfo = new JedisShardInfo("192.168.50.20",6361,"1");
//        List<JedisShardInfo> jedisShardInfoList = new ArrayList<>();
//        jedisShardInfoList.add(jedisShardInfo);
//
//        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig,jedisShardInfoList);
//
//        ShardedJedisCacheManager cacheManager = new ShardedJedisCacheManager(shardedJedisPool,iSerializer);
//        return cacheManager;
//    }
//
//    @Bean
//    public CacheHandler cacheHandler(ICacheManager cacheManager, @Qualifier("springELParser") AbstractScriptParser scriptParser,
//                                     AutoLoadConfig config, @Qualifier("dd") ICloner cloner{
//        return new CacheHandler(cacheManager,scriptParser,config,cloner);
//    }
//
//}
