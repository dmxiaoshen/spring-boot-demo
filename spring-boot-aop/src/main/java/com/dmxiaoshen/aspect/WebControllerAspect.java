package com.dmxiaoshen.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by hzhsg on 2017/11/14.
 */
@Component
@Aspect
public class WebControllerAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.dmxiaoshen.controller..*.*(..))")
    public void log(){};

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws  Throwable{
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "rtv", pointcut = "log()")
    public void doAfterReturning(JoinPoint joinPoint,Object rtv) throws Throwable {
        // 处理完请求，返回内容
        logger.info("Method Signature: "  + joinPoint.getSignature());
        logger.info("Returning:" + rtv );
    }

    /**
     *  这里@Around需要注意一定要把返回值返回，不然void的话所有被切入的方法都丢失返回值，
     *  这不是我们的初衷，我们应该把值传递出来
     * @param pip
     * @return
     * @throws Throwable
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint pip) throws Throwable{
        long start = System.currentTimeMillis();
        logger.info("开始时间:"+start);
        Object obj = pip.proceed();
        long end = System.currentTimeMillis();
        logger.info("结束时间:"+end);
        logger.info("耗时:"+(end-start));
        return obj;
    }
}
