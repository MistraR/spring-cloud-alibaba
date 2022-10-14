package com.sample.mall.user.aspect;

import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    private static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 进入方法时间戳
     */
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    public WebLogAspect() {
    }


    /**
     * 定义请求日志切入点，其切入点表达式有多种匹配方式,这里是指定路径
     */
    @Pointcut("execution(public * com.sample.mall.user.controller.*.*(..))")
    public void webLogPointcut() {
    }

    /**
     * 前置通知：
     * 1. 在执行目标方法之前执行，比如请求接口之前的登录验证;
     * 2. 在前置通知中设置请求日志信息，如开始时间，请求参数，注解内容等
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLogPointcut()")
    public void doBefore(JoinPoint joinPoint) {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取请求头中的User-Agent
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        //打印请求的内容
        startTime.set(System.currentTimeMillis());
        log.info("* 请求开始 *\t: {}", LocalDateTime.now());
        log.info("请求Url\t\t: {}", request.getRequestURL().toString());
        log.info("请求方式\t\t: {}", request.getMethod());
        log.info("请求ip\t\t: {}", request.getRemoteAddr());
        log.info("请求方法\t\t: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("请求参数\t\t: {}", Arrays.toString(joinPoint.getArgs()));
        // 系统信息
        log.info("浏览器\t\t: {}", userAgent.getBrowser().toString());
        log.info("浏览器版本\t: {}", userAgent.getBrowserVersion());
        log.info("操作系统\t\t: {}", userAgent.getOperatingSystem().toString());
    }

    /**
     * 返回通知：
     * 1. 在目标方法正常结束之后执行
     * 1. 在返回通知中补充请求日志信息，如返回时间，方法耗时，返回值，并且保存日志信息
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "webLogPointcut()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("请求耗时\t\t: {}", (System.currentTimeMillis() - startTime.get()));
        log.info("请求返回\t\t: {}", ret);
        log.info("* 请求结束 *\t: {}", LocalDateTime.now());
    }

    /**
     * 异常通知：
     * 1. 在目标方法非正常结束，发生异常或者抛出异常时执行
     * 1. 在异常通知中设置异常信息，并将其保存
     *
     * @param throwable
     */
    @AfterThrowing(value = "webLogPointcut()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        // 保存异常日志记录
        log.error("发生异常时间：{}", LocalDateTime.now());
        log.error("抛出异常：{}", throwable.getMessage());
    }
}
