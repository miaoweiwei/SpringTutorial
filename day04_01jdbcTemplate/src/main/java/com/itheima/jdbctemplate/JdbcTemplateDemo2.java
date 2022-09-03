package com.itheima.jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * JdbcTemplate 的最基本用法
 */
public class JdbcTemplateDemo2 {
    public static void main(String[] args) {
        //1.获取IOC容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取bean对象
        JdbcTemplate jt = (JdbcTemplate) ac.getBean("jdbcTemplate");
        //3.执行操作
        jt.execute("insert into account(name, money) values ('ccc', 2222)");
    }
}