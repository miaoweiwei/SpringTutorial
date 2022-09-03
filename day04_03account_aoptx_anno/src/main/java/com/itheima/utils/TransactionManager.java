package com.itheima.utils;

import com.sun.xml.internal.ws.model.RuntimeModelerException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类，包含了，开启事务、提交事务、回滚事务和释放连接
 */
@Component("txManager")
@Aspect // 表明当前类是一个切面
public class TransactionManager {
    @Autowired
    private ConnectionUtils connectionUtils;

    /**
     * 这个方法用于表示切入点表达式
     */
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1() {
    }

    /**
     * 开始事务
     */
    //@Before("pt1()") // 引用切入点表达式时必须要写pt1后面的()
    public void beginTransaction() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    //@AfterReturning("pt1()")
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    //@AfterThrowing("pt1()")
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    //@After("pt1()")
    public void release() {
        try {
            connectionUtils.getThreadConnection().close(); // 把连接返回连接池中
            connectionUtils.removeConnection();//把连接和线程进行解绑
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Around("pt1()") // 环绕通知
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            //1.获取参数
            Object[] args = pjp.getArgs();
            //2.开启事务
            this.beginTransaction();
            //3.执行方法
            rtValue = pjp.proceed(args);
            //4.提交事务
            this.commit();

            return rtValue;

        } catch (Throwable e) { // Exception 不能捕获到这个异常
            //5.回滚
            this.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放事务
            this.release();
        }
    }
}
