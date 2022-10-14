package com.sample.mall.common.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 自定义限流相关的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRateLimiter {

    /**
     * 每秒生成的令牌数
     *
     * @return
     */
    double permitsPerSecond();

    /**
     * 是否预热
     *
     * @return
     */
    boolean isWarmup() default false;

    /**
     * 预热时间
     *
     * @return
     */
    long warmupPeriod() default 1;

    /**
     * 预热时间单位
     *
     * @return
     */
    TimeUnit warmupTimeUnit() default TimeUnit.SECONDS;

    /**
     * 一次获取多少个令牌
     *
     * @return
     */
    int permits() default 1;

    /**
     * 获取令牌的最大等待时间
     *
     * @return
     */
    long timeout();

    /**
     * 获取令牌最大等待时间的单位，这里默认是毫秒
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

}
