package com.kkrunning.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.decoder.CodecDecoder;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
//@EnableScheduling
public class RedisConfigManager {


    @Bean(destroyMethod = "shutdown")
    RedissonClient redissonClient() throws IOException {
        Config config = new Config();
//        config.setCodec(JsonJacksonCodec.INSTANCE);
        config.useSingleServer().setAddress("redis://116.198.242.91:16379");
//                .setPassword("0754zzk");
        return Redisson.create(config);
    }

    @Bean("redissionCache")
    CacheManager redissionCache(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<>();
        // 创建一个名称为"testMap"的缓存，过期时间ttl为24分钟，同时最长空闲时maxIdleTime为12分钟。
        CacheConfig cacheConfig = new CacheConfig(2 * 60 * 1000, 2 * 60 * 1000);
        cacheConfig.setMaxSize(5);
        config.put("task", cacheConfig);
        // 我希望配置这个cache的缓存过期时间，最大缓存数目，淘汰策略
        return new RedissonSpringCacheManager(redissonClient, config);
    }


}