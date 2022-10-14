package com.sample.mall.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义缓存注解
 * 用于在方法执行之前判断缓存是否存在
 * 存在则直接返回
 * 不存在则查数据库，再设置缓存
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyCacheable {

    /**
     * 缓存的名称的前缀，完整的缓存名称生成规则：{cacheName}:{key}
     * @return
     */
    String cacheName();

    /**
     * 缓存的Key的表达式，可以使用SpEL表达式，用于匹配参数值
     * @return
     */
    String key();

    /**
     * 缓存的过期时间，单位为秒，默认不设置过期时间
     * @return
     */
    int expireInSeconds() default 0;

    /**
     * 限流器获取令牌等待超时时间
     * @return
     */
    int waitInSeconds() default 0;

    String bloomFilterName() default "";
}
