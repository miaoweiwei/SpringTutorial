package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，它里面提共了公共的方法
 */
@Component("logger")
@Aspect//表示当前类是一个切面类
public class Logger {
    /**
     * 这个方法用于表示切入点表达式
     */
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1() {
    }

    /**
     * 前置通知
     */
//    @Before("pt1()") // 引用切入点表达式时必须要写pt1后面的()
    public void beforePrintLog() {
        System.out.println("前置通知:Logger类中的beforePrintLog方法开始记录日志了...");
    }

    /**
     * 后置通知
     */
//    @AfterReturning("pt1()")
    public void afterReturningPrintLog() {
        System.out.println("后置通知:Logger类中的afterReturningPrintLog方法开始记录日志了...");
    }

    /**
     * 异常通知
     */
//    @AfterThrowing("pt1()")
    public void afterThrowingPrintLog() {
        System.out.println("异常通知:Logger类中的afterThrowingPrintLog方法开始记录日志了...");
    }

    /**
     * 最终通知
     */
//    @After("pt1()")
    public void afterPrintLog() {
        System.out.println("最终通知:Logger类中的afterPrintLog方法开始记录日志了...");
    }

    /*
     * 环绕通知
     * 问题:
     *      当我们配置了环绕通知之后,切入点方法没有执行,而通知方法执行了
     * 分析:
     *      通过对比动态代理中的环绕通知代码,发现动态代理的环绕通知有明确的切入点调用,而我们的代码中没有
     * 解决方法：
     *      Spring框架为我们提供的一个接口：ProceedingJoinPoint。该接口有一个proceed(),此方法就相当于明确调用切入点方法。
     *      该接口可以作为环绕通知的方法参数，在程序执行时，Spring框架会为我们提供该接口的实现类工我们使用。
     * Spring中的环绕通知：
     *      它是Spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
     */
    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint pjp) {
        Object rtValue;
        try {
            Object[] args = pjp.getArgs();
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了...前置"); // 写在这里表示前置通知
            rtValue = pjp.proceed(args);// 明确调用业务层方法
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了...后置"); // 写在这里表示后置通知
            return rtValue;
        } catch (Throwable throwable) { // 这里要使用Throwable来拦截异常
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了...异常"); // 写在这里表示异常通知
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了...最终"); // 写在这里表示最终通知
        }
    }
}
