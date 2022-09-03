package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，它里面提共了公共的方法
 */
@Component("logger")
@Aspect//表示当前类是一个切面类
public class Logger {
    /**
     * 这个方法用于表示切入点表达式，要代理的方法通过切入点表达式解析得到
     */
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1() {
    }

    /**
     * 这里使用使用 @annotation 标识要使用哪个注解来进行代理
     * 这里 @annotation() 里填写注解的名字
     * 要代理的方法上必须要写该注解
     * 注意 这种方法只能对该注解写在方法起作用，只能对写该注解的方法进行代理
     * 注意 如果注解和当前的这个切面类不在同一个包里就要写全这个注解的路径
     *     如果注解和当前的这个切面类在同一个包里就不用写全这个注解的路径，只填注解名就行
     */
    @Pointcut("@annotation(com.itheima.annotation.LoggerAnnotation)")
    private void ptAnnotation() {
    }

    /**
     * 这里 @within 与 @annotation 作用类似
     * 唯一不同的是作用范围比 @annotation 更广，对与对应的注解放到类上面，就可以把这个类里的所有的方法都代理
     * 要代理的类上必须要写该注解，类中的方法可以不用写注解
     */
    @Pointcut("@within(com.itheima.annotation.LoggerAnnotation)")
    private void ptWithin() {
    }

    /*
     * 环绕通知
     */
    //@Around("pt1()")
    //@Around("ptAnnotation()")
    //@Around("ptWithin()")
    @Around("@within(com.itheima.annotation.LoggerAnnotation)")
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
