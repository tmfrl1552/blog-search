package com.seulgi.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    @Value("${cache.expire_second}")
    private Integer expireSeconds;

    @Getter
    public enum CaffeineCacheType {
        POPULAR_KEYWORDS("popularKeywords", 10, 10000);

        private String cacheName;

        private Integer expiredAfterWrite;

        private int maximumSize;

        CaffeineCacheType(String cacheName, Integer expiredAfterWrite, int maximumSize) {
            this.cacheName = cacheName;
            this.expiredAfterWrite = expiredAfterWrite;
            this.maximumSize = maximumSize;
        }
    }

    @Bean
    @Primary
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = Arrays.stream(CaffeineCacheType.values())
                .map(cache -> new CaffeineCache(cache.getCacheName(), Caffeine.newBuilder().recordStats()
                                .expireAfterWrite(
                                        Objects.isNull(cache.getExpiredAfterWrite())
                                                ? expireSeconds
                                                : cache.getExpiredAfterWrite(),
                                        TimeUnit.SECONDS)
                                .maximumSize(cache.getMaximumSize())
                                .build()))
                .collect(Collectors.toList());

        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
