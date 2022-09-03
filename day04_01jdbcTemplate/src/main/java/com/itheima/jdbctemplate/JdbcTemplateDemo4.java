package com.itheima.jdbctemplate;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate 的最基本用法
 */
public class JdbcTemplateDemo4 {
    public static void main(String[] args) {
        //1.获取IOC容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取bean对象
        IAccountDao accountDao = (IAccountDao) ac.getBean("accountDao");
        //3.执行操作
        Account account = accountDao.findAccountById(1);
        System.out.println(account);

        account.setMoney(30000f);
        //执行更新操作
        accountDao.updateAccount(account);
    }
}