package com.ksmirnov.demoapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(3)
public class ServiceAspect {

    @Around("execution(* getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("===> @Around on " + method);

        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println("===> @Around is executed for " + duration / 1000.0 + " seconds");

        return result;
    }

    @Around("execution(* throwOneException(..))")
    public Object aroundThrowOneException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("===> @Around on " + method);

        Object result = null;
        long begin = System.currentTimeMillis();
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception exc) {
            System.out.println("===> @Around threw an exception: " + exc.getMessage());
        }
        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println("===> @Around is executed for " + duration / 1000.0 + " seconds");

        return result;
    }

    @Around("execution(* throwAnotherException(..))")
    public Object aroundThrowAnotherException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("===> @Around on " + method);

        Object result = null;
        long begin = System.currentTimeMillis();
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception exc) {
            System.out.println("===> @Around threw an exception: " + exc.getMessage());
            throw exc;
        }
        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println("===> @Around is executed for " + duration / 1000.0 + " seconds");

        return result;
    }
}
