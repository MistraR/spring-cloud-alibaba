package com.sample.mall.goods;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Before;
import org.junit.Test;

public class GuavaBloomFilterTest {

    // 预期的数据量
    private int size = 1000;

    // 期望的误差率
    private double fpp = 0.01;

    // 创建布隆过滤器，Funnel指定布隆过滤器中存的是什么类型的数据
    private BloomFilter <Integer> bloomFilter =
            BloomFilter.create(Funnels.integerFunnel(), size, fpp);;

    @Before
    public void initBloomFilter() {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
    }

    @Test
    public void testGuavaBloomFilter() {
        int count = 0;
        for (int j = size; j < size * 2; j++) {
            if(bloomFilter.mightContain(j)) {
                count++;
                System.out.println(j + "误判了");
            }
        }
        System.out.println("误判个数：" + count);
    }
}
