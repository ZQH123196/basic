package org.example;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * https://github.com/ben-manes/caffeine/wiki/Population-zh-CN
 */
public class SimpleTest {

    @Test
    public void test1() {
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(10_000)
                .build();

        String key = "key";
        // 查找一个缓存元素， 没有查找到的时候返回null
        String value = cache.getIfPresent(key);
        System.out.println(value);

        // 查找缓存，如果缓存不存在则生成缓存元素, 如果生成也失败了则返回 null
        // This method provides a simple substitute for the conventional "if cached, return; otherwise create, cache and return" pattern.
        // //如果 cache 不存在，就去获取并存入缓存，比如不存在就去数据库获取并存入缓存
        value = cache.get(key, k -> "123");
        System.out.println(value);

        value = cache.getIfPresent(key);
        System.out.println(value);


        // 添加或者更新一个缓存元素
        cache.put(key, "8999");
        // 已经有值，因此回调不会被触发了
        value = cache.get(key, k -> "123");
        System.out.println(value);


        // 移除一个缓存元素
        cache.invalidate(key);
        value = cache.getIfPresent(key);
        System.out.println(value);
    }
}
