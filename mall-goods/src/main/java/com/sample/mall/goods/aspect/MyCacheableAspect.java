package com.sample.mall.goods.aspect;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.sample.mall.common.annotation.MyCacheable;
import com.sample.mall.common.base.ResponseEnum;
import com.sample.mall.common.exception.BusinessException;
import io.rebloom.client.Client;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
@ConfigurationProperties(prefix = "mycacheable.rate.limit")
public class MyCacheableAspect {

    private static final Logger logger = LoggerFactory.getLogger(MyCacheableAspect.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private Client redisBloomClient;

    public void setMap(Map<String, Double> map) {
        this.map = map;
    }

    /**
     * key - 需要限流的方法
     * value - 限流速率
     */
    private Map<String, Double> map;

    private Map<String, RateLimiter> rateLimiterMap = Maps.newHashMap();

    @PostConstruct
    private void initRateLimiterMap() {
        if(!CollectionUtils.isEmpty(map)) {
            map.forEach((methodName, permits) -> {
                RateLimiter rateLimiter = RateLimiter.create(permits);
                rateLimiterMap.put(methodName, rateLimiter);
            });
        }
    }

    @Around("@annotation(myCacheable)")
    public Object doAroundCache(ProceedingJoinPoint joinPoint, MyCacheable myCacheable) throws Throwable {

        // 获取缓存Key
        String cacheKey = getCacheKey(joinPoint, myCacheable);

        // 布隆过滤
        this.bloomFilter(myCacheable, cacheKey);

        // 判断缓存是否存在，如果存在则直接返回
        Object value = redisTemplate.opsForValue().get(cacheKey);
        if(value != null) {
            logger.info("key:{}, value:{}", cacheKey, value);
            return value;
        }

        this.rateLimit(joinPoint, myCacheable);

        // 查询数据库
        value = joinPoint.proceed();

        // 过期时间的处理
        if(myCacheable.expireInSeconds() <= 0) {
            redisTemplate.opsForValue().set(cacheKey, value);
        } else {
            redisTemplate.opsForValue().set(cacheKey, value, myCacheable.expireInSeconds(), TimeUnit.SECONDS);
        }

        return value;
    }

    private void bloomFilter(MyCacheable myCacheable, String cacheKey) {
        String bloomFilterName = myCacheable.bloomFilterName();
        if(StringUtils.isNotBlank(bloomFilterName)) {
            boolean exists = redisBloomClient.exists(bloomFilterName, cacheKey);
            if(!exists) {
                throw new BusinessException(ResponseEnum.ENTITY_NOT_FOUND);
            }
        }
    }

    private void rateLimit(ProceedingJoinPoint joinPoint, MyCacheable myCacheable) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RateLimiter rateLimiter = rateLimiterMap.get(signature.getMethod().getName());
        if(rateLimiter != null) {
            int timeout = myCacheable.waitInSeconds();
            if(timeout <= 0) {
                rateLimiter.acquire();
            } else {
                boolean acquired = rateLimiter.tryAcquire(timeout, TimeUnit.SECONDS);
                if(!acquired) {
                    throw new BusinessException(ResponseEnum.SYSTEM_BUSY);
                }
            }
        }
    }

    private String getCacheKey(ProceedingJoinPoint joinPoint, MyCacheable myCacheable) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(myCacheable.key());
        EvaluationContext context = new StandardEvaluationContext();

        // 动态地设置上下文环境
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(methodSignature.getMethod());
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        // cacheKey = mall:goods:1
        String cacheKey = myCacheable.cacheName() + expression.getValue(context).toString();
        return cacheKey;
    }
}
