package com.ksmirnov.demoapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class TestAspect1 {

    @Before("com.ksmirnov.demoapp.aspect.AspectExpressions.forAddAccountMethod()")
    public void beforeAddAccountAdvice() {
        System.out.println("===> @Before on addAccount()");
    }

    @Before("forAddAccountMethod()")
    public void beforeAddAccountCopyAdvice() {
        System.out.println("===> @Before copy on addAccount()");
    }

    @Before("execution(public void com.ksmirnov.demoapp.dao.AccountDAOImpl.addAccount())")
    public void beforeAccountDAOAddAccountAdvice() {
        System.out.println("===> @Before on AccountDAO.addAccount()");
    }

    @Before("execution(* com.ksmirnov.demoapp.dao.*.*(com.ksmirnov.demoapp.model.Account))")
    public void beforeAccountParameterAdvice() {
        System.out.println("===> @Before on *.*(Account)");
    }

    @Before("execution(public * com.ksmirnov.demoapp.dao.*.*(com.ksmirnov.demoapp.model.Account, ..))")
    public void beforeAnyParameterAdvice(JoinPoint joinPoint) {
        System.out.println("===> @Before on *.*(Account, ..)");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("======> Method signature: " + methodSignature);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("=========> " + arg);
        }
    }

    @Pointcut("execution(public void com.ksmirnov.demoapp.dao.MembershipDAO.*(..))")
    private void forMembershipDAOMethods() {}

    @Pointcut("execution(public void com.ksmirnov.demoapp.dao.MembershipDAO.get*(..))")
    private void forMembershipDAOGetterMethod() {}

    @Pointcut("execution(public void com.ksmirnov.demoapp.dao.MembershipDAO.set*(..))")
    private void forMembershipDAOSetterMethod() {}

    @Before("forMembershipDAOMethods() && !(forMembershipDAOGetterMethod() || forMembershipDAOSetterMethod())")
    public void beforeMembershipDAONoGetterOrSetterAdvice() {
        System.out.println("===> @Before on MembershipDAO.*(..); no getters or setters");
    }
}
