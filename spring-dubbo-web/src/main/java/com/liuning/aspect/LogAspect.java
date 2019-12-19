package com.liuning.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private static ExecutorService logExecutorService = Executors.newFixedThreadPool(10);

    @Pointcut("execution(* com.liuning.controller.*.*(..))")
    public void LogAspect() {
    }

    @Before("LogAspect()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("Do Before");
    }

    @After("LogAspect()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("doAfter");
    }

    /**
     * AfterReturning在方法执行后返回一个结果后执行
     */
    @AfterReturning("LogAspect()")
    public void doAfterReturning(JoinPoint joinPoint) {
        logger.info("doAfterReturning");
        logExecutorService.submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("23333333"+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
     */
    @Around("LogAspect()")
    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Do around after");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String apiName = request.getParameter("apiName");
        logger.info("apiName is : {}", apiName);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        Object object = joinPoint.proceed();
        logger.info("Do around after");
        return object;
    }
}
