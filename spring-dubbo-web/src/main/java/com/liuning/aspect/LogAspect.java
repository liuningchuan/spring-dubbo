package com.liuning.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuning.concurrent.GenericThreadPoolExecutor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    @Qualifier("threadPoolExecutor")
    private GenericThreadPoolExecutor logExecutorService;

    @Pointcut("execution(* com.liuning.controller.*.*(..))")
    public void LogAspect() {
    }

    @Before("LogAspect()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info("Request Paramters is {}", args);
    }

    @After("LogAspect()")
    public void doAfter(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        BusiLog busiLog = method.getAnnotation(BusiLog.class);
        logger.info("接口名称：" + busiLog.name());

        Object[] args = joinPoint.getArgs();
        logger.info("do {}", args);
    }

    /**
     * AfterReturning在方法执行后返回一个结果后执行
     */
    @AfterReturning("LogAspect()")
    public void doAfterReturning(JoinPoint joinPoint) {
        logger.info("doAfterReturning");
        logExecutorService.submit(() -> {
            logger.info("asynchronous thread");
        });
    }

    /**
     * AfterThrowing在方法执行过程中抛出异常的时候执行
     */
    @AfterThrowing("LogAspect()")
    public void deAfterThrowing(JoinPoint joinPoint) {
        logger.info("deAfterThrowing");
    }

    /**
     * Around环绕通知，在执行前后都使用，这个方法参数必须为ProceedingJoinPoint
     * 从BusiLog注解中获取调用接口名称
     */
    @Around("LogAspect()&&@annotation(log)")
    public Object deAround(ProceedingJoinPoint joinPoint, BusiLog log) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getRequest();
        String apiName = request.getParameter("apiName");
        logger.info("apiName is : {}, interface is {}", apiName, log.name());
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        Object[] args = joinPoint.getArgs();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Request is {}", objectMapper.writeValueAsString(args));
        Object object = joinPoint.proceed(args);
        logger.info("Response is {}", objectMapper.writeValueAsString(object));
        return object;
    }
}
