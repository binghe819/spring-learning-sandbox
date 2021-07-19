package com.binghe.redis_template;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTemplateService {
    private static final String KEY = "pickgit";

    private StringRedisTemplate redisTemplate;
    private HashOperations<String, String, String> stringStringHashOperations;

    public RedisTemplateService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringStringHashOperations = redisTemplate.opsForHash();
    }

    public String save(String key, String value) {
        stringStringHashOperations.putIfAbsent(KEY, key, value);
        return stringStringHashOperations.get(KEY, key);
    }
}
