package com.ksmirnov.demoapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.ksmirnov.demoapp.controller.*.*(..))")
    private void logControllerPackage() {}

    @Pointcut("execution(* com.ksmirnov.demoapp.service.*.*(..))")
    private void logServicePackage() {}

    @Pointcut("execution(* com.ksmirnov.demoapp.dao.*.*(..))")
    private void logDAOPackage() {}

    @Pointcut("logControllerPackage() || logServicePackage() || logDAOPackage()")
    private void logApp() {}

    @Before("logApp()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> @Before: calling a method - " + method);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info("==========>> argument: " + arg);
        }
    }

    @AfterReturning(pointcut="logApp()", returning="result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> @AfterReturning: from a method - " + method);
        logger.info("==========>> result: " + result);
    }
}
