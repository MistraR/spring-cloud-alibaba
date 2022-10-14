package com.sample.mall.goods;

import io.rebloom.client.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisBloomFilterTest {

    @Resource
    private Client redisBloomClient;

    @Value("${spring.redis.bloom-filter.name}")
    private String name;

    @Value("${spring.redis.bloom-filter.init-capacity}")
    private int size;

    @Before
    public void init() {
        for (int i = 0; i < size; i++) {
            redisBloomClient.add(name, String.valueOf(i));
        }
    }

    @Test
    public void testRedisBloom() {
        int count = 0;
        for (int j = size; j < size * 2; j++) {
            if (redisBloomClient.exists(name, String.valueOf(j))) {
                count++;
                System.out.println(j + "误判了");
            }
        }
        System.out.println("误判个数：" + count);
    }
}
