package com.binghe;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

@DataRedisTest
class RedisTemplateTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @DisplayName("opsForValue (Interface ValueOperation) - Strings를 쉽게 직렬/역직렬화 해준다.")
    @Test
    void opsForValue() {
        // given
        String key = "string key";
        String value = "string value";

        // when
        redisTemplate.delete(key);
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();

        stringStringValueOperations.set(key, value); // set
        String result = stringStringValueOperations.get(key); //get

        assertThat(result).isEqualTo(value);
    }

    @DisplayName("opsForList (Interface ListOperations) - List를 쉽게 직렬/역직렬화 해준다.")
    @Test
    void opsForLisT() {
        // given
        String key = "list key";

        // when
        redisTemplate.delete(key);
        ListOperations<String, String> stringStringListOperations = redisTemplate.opsForList();


        stringStringListOperations.rightPush(key, "element 1");
        stringStringListOperations.rightPush(key, "element 2");

        stringStringListOperations.rightPushAll(key, "element 3", "element 4");

        // then
        String firstElement = stringStringListOperations.index(key, 0);
        Long size = stringStringListOperations.size(key);
        List<String> wholeList = stringStringListOperations.range(key, 0, size);

        assertThat(firstElement).isEqualTo("element 1");
        assertThat(size).isEqualTo(4);
        assertThat(wholeList)
            .containsExactly("element 1", "element 2", "element 3", "element 4");
    }

    @DisplayName("opsForSet (Interface SetOperation) - Set을 쉽게 직렬/역직화 해준다.")
    @Test
    void opsForSet() {
        // given
        String key = "set key";
        String value = "set value";

        // when
        redisTemplate.delete(key);
        SetOperations<String, String> stringStringSetOperations = redisTemplate.opsForSet();

        stringStringSetOperations.add(key, value);
        stringStringSetOperations.add(key, value);

        Set<String> result = stringStringSetOperations.members(key);
        Long size = stringStringSetOperations.size(key);
        assertThat(result).containsExactly(value);
        assertThat(size).isEqualTo(1);
    }

    @DisplayName("opsForZSet (Interface ) - Sorted Set를 쉽게 직렬/역직렬화 해준다.")
    @Test
    void opsForZSet() {
        // given
        String key = "zset key";

        // when
        redisTemplate.delete(key);
        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();

        stringStringZSetOperations.add(key, "B", 10);
        stringStringZSetOperations.add(key, "I", 5);
        stringStringZSetOperations.add(key, "N", 1);
        stringStringZSetOperations.add(key, "N", 6);
        stringStringZSetOperations.add(key, "G", 15);

        // then
        Set<String> result = stringStringZSetOperations.range(key, 0, 5);
        Long size = stringStringZSetOperations.size(key);
        assertThat(result).containsExactly("I", "N", "B", "G");
        assertThat(size).isEqualTo(4);
    }

    @DisplayName("opsForHash (Interface ) - Hash를 쉽게 직렬/역직렬화 해준다.")
    @Test
    void opsForHash() {
        // given
        String key = "hash key";

        // when
        redisTemplate.delete(key);
        HashOperations<String, String, String> stringStringStringHashOperations = redisTemplate.opsForHash();

        stringStringStringHashOperations.put(key, "key1", "value1");
        stringStringStringHashOperations.put(key, "key2", "value2");
        stringStringStringHashOperations.put(key, "key3", "value3");

        // then
        String value1 = stringStringStringHashOperations.get(key, "key1");
        Map<String, String> entries = stringStringStringHashOperations.entries(key);
        Long size = stringStringStringHashOperations.size(key);
        assertThat(value1).isEqualTo("value1");
        assertThat(entries).containsKeys("key1", "key2", "key3");
        assertThat(entries).containsValues("value1", "value2", "value3");
        assertThat(size).isEqualTo(3);
    }
}
