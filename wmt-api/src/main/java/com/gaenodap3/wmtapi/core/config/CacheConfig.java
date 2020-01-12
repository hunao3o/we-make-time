package com.gaenodap3.wmtapi.core.config;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.github.benmanes.caffeine.cache.Caffeine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
public class CacheConfig implements CommandLineRunner{
    
	@Autowired
	private CacheManager cacheManager;

    @Getter
    public enum Caches {
        CACHE_POST_BY_ID(3);

        Caches(int expireAfterWrite) {
            this.expireAfterWrite = expireAfterWrite;
        }

        Caches(int expireAfterWrite, int maximumSize) {
            this.expireAfterWrite = expireAfterWrite;
            this.maximumSize = maximumSize;
        }

        private int maximumSize = 50000;
        private int expireAfterWrite = 10;
    }

    @Bean
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = Arrays.stream(Caches.values())
                .map(cache -> new CaffeineCache(cache.name(),
                        Caffeine.newBuilder().recordStats().expireAfterWrite(cache.expireAfterWrite, TimeUnit.SECONDS)
                                .maximumSize(cache.maximumSize)
                                .build()))
                .collect(Collectors.toList());

        cacheManager.setCaches(caches);
        return cacheManager;
    }

	@Override
	public void run(final String... args) throws Exception {
		System.out.println("cacheManager.getCacheNames(): {}"+ cacheManager.getCacheNames());
	}
}