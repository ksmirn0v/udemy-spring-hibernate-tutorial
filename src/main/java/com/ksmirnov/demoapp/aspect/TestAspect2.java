package com.ksmirnov.demoapp.aspect;

import com.ksmirnov.demoapp.model.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(1)
public class TestAspect2 {

    @Before("execution(public void add*())")
    public void beforeAddAnyAdvice() {
        System.out.println("===> @Before on add*()");
    }

    @AfterReturning(pointcut="execution(* findAccounts(..))", returning="result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        System.out.println("===> @AfterReturning on findAccounts(..)");
        System.out.println("=======> result is " + result);
        result.add(new Account("George", "Clooney"));
    }

    @AfterThrowing(pointcut="execution(* findMembers(..))", throwing="exc")
    public void afterThrowingFindMembersAdvice(JoinPoint joinPoint, Throwable exc) {
        System.out.println("===> @AfterThrowing on findMembers(..)");
    }

    @After("execution(* findMembers(..))")
    public void afterFindMembersAdvice() {
        System.out.println("===> @After on findMembers(..)");
    }
}
