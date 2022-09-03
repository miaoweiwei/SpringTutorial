package com.itheima.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标识需要记录日志的注解，
 * ElementType.METHOD 表示这个注解可以放在方法上
 * ElementType.TYPE  表示这个注解可以放到 类、接口上
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerAnnotation {
}
