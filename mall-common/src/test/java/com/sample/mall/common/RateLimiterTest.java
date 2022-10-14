package com.sample.mall.common;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RateLimiterTest {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

    /**
     * create(double permitsPerSecond)
     * 根据指定的稳定吞吐率创建RateLimiter，这里的吞吐率是指每秒多少许可数（通常是指QPS，或者字节数）
     */
    @Test
    public void testCreate() {
        RateLimiter rateLimiter = RateLimiter.create(1);
        System.out.println(rateLimiter.getRate());

        rateLimiter.setRate(2);
        System.out.println(rateLimiter.getRate());
    }

    /**
     * acquire (int permits)
     * 从RateLimiter获取指定许可数，该方法会被阻塞直到获取到请求
     */
    @Test
    public void testAcquire() {
        RateLimiter rateLimiter = RateLimiter.create(1);
        while (true) {
            boolean result = rateLimiter.tryAcquire(1);
            System.out.println(result);
        }
    }

    /**
     * 预消费
     */
    @Test
    public void testAcquire2() throws Exception {

        RateLimiter rateLimiter = RateLimiter.create(1);

        acquireAndPrint(rateLimiter, 5);
        acquireAndPrint(rateLimiter, 2);
        acquireAndPrint(rateLimiter, 5);

        sleepAndPrint(3000);

        acquireAndPrint(rateLimiter, 5);

        sleepAndPrint(10000);

        acquireAndPrint(rateLimiter, 4);
        acquireAndPrint(rateLimiter, 1);
    }

    private void acquireAndPrint(RateLimiter rateLimiter, int permits) {
        double timeSpent = rateLimiter.acquire(permits);
        System.out.println(sdf.format(new Date()) + " [" + permits + "] " + timeSpent);
    }

    private void sleepAndPrint(long millis) throws Exception {
        Thread.sleep(millis);
        System.out.println(sdf.format(new Date()) + " 等了" + millis / 1000 + "秒钟...");
    }

    /**
     * tryAcquire(int permits, long timeout, TimeUnit unit)
     * 从 RateLimiter 获取指定许可数，如果该许可数可以在不超过timeout的时间内获取得到的话，或者如果无法在timeout过期之前获取得到许可数的话，那么立即返回false（无需等待）
     */
    @Test
    public void testTryAcquire() {
        RateLimiter rateLimiter = RateLimiter.create(1);

        boolean result1 = rateLimiter.tryAcquire(10);
        System.out.println("获取10个令牌结束, 结果：" + result1);

        boolean result2 = rateLimiter.tryAcquire(1, 3, TimeUnit.SECONDS);
        System.out.println("获取1个令牌结束, 结果：" + result2);
    }

    /**
     * create(double permitsPerSecond,long warmupPeriod,TimeUnit unit)
     * 根据指定的稳定吞吐率和预热期来创建RateLimiter，这里的吞吐率是指每秒多少许可数（通常是指QPS，每秒多少查询）
     */
    @Test
    public void testSmoothWarmingUp() {
        RateLimiter rateLimiter = RateLimiter.create(2, 4, TimeUnit.SECONDS);
        while (true) {
            System.out.println("get 1 token: " + rateLimiter.acquire(1) + "s");
        }
    }

}
