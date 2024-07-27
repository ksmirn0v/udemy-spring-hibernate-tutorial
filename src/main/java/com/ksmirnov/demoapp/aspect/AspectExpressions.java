package com.ksmirnov.demoapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectExpressions {

    @Pointcut("execution(public void addAccount())")
    public void forAddAccountMethod() {}
}
