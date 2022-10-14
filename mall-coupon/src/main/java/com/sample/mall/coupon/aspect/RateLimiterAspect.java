package com.sample.mall.coupon.aspect;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.sample.mall.common.annotation.MyRateLimiter;
import com.sample.mall.common.base.BaseResponse;
import com.sample.mall.common.base.ResponseEnum;
import com.sample.mall.common.util.JSONUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 定义一个切面，用于实现限流
 */
@Aspect
@Component
public class RateLimiterAspect {

    private static final Logger log = LoggerFactory.getLogger(RateLimiterAspect.class);

    /**
     * 以资源名称为key值，存储多个的限流器
     */
    private Map<String, RateLimiter> limiters = Maps.newConcurrentMap();

    /**
     * 创建切面，具体实现限流逻辑
     */
    @Around("@annotation(myRateLimiterAnnotation)")
    public Object process(ProceedingJoinPoint point, MyRateLimiter myRateLimiterAnnotation) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String resource = request.getRequestURI();

        log.info("{}开始进行限流处理...", resource);

        RateLimiter rateLimiter = this.getRateLimiter(resource, myRateLimiterAnnotation);
        boolean acquired = rateLimiter.tryAcquire(
                myRateLimiterAnnotation.permits(),
                myRateLimiterAnnotation.timeout(),
                myRateLimiterAnnotation.timeUnit()
        );
        if (!acquired) {
            // 没有获取到令牌需要限流处理
            log.warn("{}触发限流限制了！", resource);
            this.outputResponse();
        }
        return point.proceed();
    }

    /**
     * 获取 RateLimiter 对象，如果容器中没有对应资源的限流器，先创建一个限流器
     * @param resource
     * @param myRateLimiterAnnotation
     * @return
     */
    private RateLimiter getRateLimiter (String resource, MyRateLimiter myRateLimiterAnnotation) {
        RateLimiter rateLimiter = limiters.get(resource);
        if (rateLimiter == null) {
            rateLimiter = this.createRateLimiter(myRateLimiterAnnotation);
            limiters.put(resource, rateLimiter);
        }
        return rateLimiter;
    }

    /**
     * 新建限流器，根据参数分别创建平滑预热限流器或者是平滑突发限流器
     * @param myRateLimiterAnnotation
     * @return
     */
    private RateLimiter createRateLimiter (MyRateLimiter myRateLimiterAnnotation) {
        RateLimiter rateLimiter;
        if (myRateLimiterAnnotation.isWarmup()) {
            // 以平滑预热方法创建限流器
            rateLimiter = RateLimiter.create(
                    myRateLimiterAnnotation.permitsPerSecond(),
                    myRateLimiterAnnotation.warmupPeriod(),
                    myRateLimiterAnnotation.warmupTimeUnit()
            );
        } else {
            // 以平滑突发方法创建限流器
            rateLimiter = RateLimiter.create(myRateLimiterAnnotation.permitsPerSecond());
        }
        return rateLimiter;
    }

    private void outputResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            BaseResponse baseResponse = new BaseResponse(ResponseEnum.SYSTEM_BUSY);
            outputStream.write(JSONUtil.toJSONString(baseResponse).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (Exception e) {
            log.error("输出响应异常", e);
        }
    }
}
