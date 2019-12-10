package com.choi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author : choibin
 * @date : 13:42  2019/11/26 0026
 */
@Component
@Aspect
public class LogComponent {

    @Pointcut("execution(* com.choi.aop.service.*.*(..))")
    public void pc1(){

    }

    @Before(value = "pc1()")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println("before--" + name);
    }

    @After(value = "pc1()")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println("after--" + name);
    }

    @AfterReturning(value = "pc1()",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println("afterReturning----" + name);
    }

    @AfterThrowing(value = "pc1()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Exception e){
        String name = joinPoint.getSignature().getName();
        System.out.println("afterThrowing---" + name);
    }

    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws  Throwable{
        Object proceed = proceedingJoinPoint.proceed();
        return "test aop";
    }



}
